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
            PermissionControlled permissionControlled = beanPropertyWriter.findAnnotation(PermissionControlled.class);
            String[] include = permissionControlled.include();
            String[] exclude = permissionControlled.exclude();
            if (List.of(include).contains("test")) {
                beanPropertyWriter.assignNullSerializer(new NullSerializer());
            }
        }


        return super.changeProperties(config, beanDesc, beanProperties);
    }

    private static class NullSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeObject(null);
        }

    }

}
