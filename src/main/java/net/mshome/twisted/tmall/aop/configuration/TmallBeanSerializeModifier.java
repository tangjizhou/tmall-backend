package net.mshome.twisted.tmall.aop.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import net.mshome.twisted.tmall.annotation.DefaultPermissionControlledValueSupplier;
import net.mshome.twisted.tmall.annotation.PermissionControlled;
import net.mshome.twisted.tmall.annotation.PermissionControlledValueSupplier;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 自定义序列化，结合shiro加入权限控制
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/2
 */
public class TmallBeanSerializeModifier extends BeanSerializerModifier {

    private static boolean isInclude(Subject subject, String[] include) {
        if (Objects.isNull(subject) || include.length == 0) {
            return true;
        }
        return Arrays.stream(include).anyMatch(subject::isPermitted);
    }

    private static boolean isExclude(Subject subject, String[] exclude) {
        if (Objects.isNull(subject) || exclude.length == 0) {
            return true;
        }
        return Arrays.stream(exclude).anyMatch(subject::isPermitted);
    }


    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {

        for (BeanPropertyWriter beanPropertyWriter : beanProperties) {
            PermissionControlled controlled = beanPropertyWriter.findAnnotation(PermissionControlled.class);
            if (Objects.isNull(controlled) || (controlled.include().length == 0 && controlled.exclude().length == 0)) {
                continue;
            }
            beanPropertyWriter.assignSerializer(new PermissionControlledJsonSerializer(beanPropertyWriter));
        }
        return beanProperties;
    }

    private static class PermissionControlledJsonSerializer extends JsonSerializer<Object> {

        private BeanPropertyWriter propertyWriter;

        public PermissionControlledJsonSerializer(BeanPropertyWriter propertyWriter) {
            this.propertyWriter = propertyWriter;
        }

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            PermissionControlled controlled = propertyWriter.findAnnotation(PermissionControlled.class);
            Subject subject = SecurityUtils.getSubject();
            String[] include = controlled.include();
            String[] exclude = controlled.exclude();

            // 无用户，或者用户有权限，写入真实的值
            if (Objects.isNull(subject) || (!isExclude(subject, exclude) && isInclude(subject, include))) {
                jsonGenerator.writeObject(o);
                return;
            }

            // 否则，打马赛克
            Class<? extends PermissionControlledValueSupplier> supplier = controlled.supplier();
            if (DefaultPermissionControlledValueSupplier.class != supplier) { // 配置了默认值提供者
                jsonGenerator.writeObject(BeanUtils.instantiateClass(supplier).supply());
            } else if (!Objects.equals(PermissionControlled.NULL, controlled.defaultValue())) { // 配置了默认值String
                jsonGenerator.writeString(controlled.defaultValue());
            } else if (propertyWriter.getType().isArrayType()) { // 如果数组类型则返回空数组
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            } else if (propertyWriter.getType().isCollectionLikeType()) { // 如果是集合则返回空集合
                jsonGenerator.writeObject(Collections.emptyList());
            } else { // 否则执行默认的默认值提供者
                jsonGenerator.writeObject(BeanUtils.instantiateClass(supplier).supply());
            }

        }

    }

}
