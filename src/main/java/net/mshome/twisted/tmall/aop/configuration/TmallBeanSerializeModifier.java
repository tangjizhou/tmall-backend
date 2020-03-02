package net.mshome.twisted.tmall.aop.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import net.mshome.twisted.tmall.annotation.PermissionControlled;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/2
 */
public class TmallBeanSerializeModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {

        for (BeanPropertyWriter beanPropertyWriter : beanProperties) {
            beanPropertyWriter.assignSerializer(new NullSerializer());
        }
        return beanProperties;
    }

    private static class NullSerializer extends JsonSerializer<Object> {


        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            String fieldName = jsonGenerator.getOutputContext().getCurrentName();
            try {
                PermissionControlled permissionControlled = jsonGenerator.getCurrentValue().getClass()
                        .getDeclaredField(fieldName).getAnnotation(PermissionControlled.class);
                if (Objects.isNull(permissionControlled)) {
                    jsonGenerator.writeObject(o);
                    return;
                }
                String[] include = permissionControlled.include();
                String[] exclude = permissionControlled.exclude();
                if (List.of(include).contains("test")) {
                    jsonGenerator.writeString(permissionControlled.defaultValue());
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }

    }

}
