package net.mshome.twisted.tmall.aop.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import net.mshome.twisted.tmall.annotation.DefaultValueSupplier;
import net.mshome.twisted.tmall.annotation.NullValueSupplier;
import net.mshome.twisted.tmall.annotation.PermissionControlled;
import net.mshome.twisted.tmall.constant.SessionKeyConstants;
import net.mshome.twisted.tmall.vo.UserAuthVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.*;

/**
 * 自定义序列化，结合shiro加入权限控制
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/2
 */
public class TmallBeanSerializeModifier extends BeanSerializerModifier {

    private static boolean isInclude(Set<String> permissions, String[] include) {
        if (Objects.isNull(permissions) || include.length == 0) {
            return true;
        }
        return Arrays.stream(include).anyMatch(permissions::contains);
    }

    private static boolean isExclude(Set<String> permissions, String[] exclude) {
        if (Objects.isNull(permissions) || exclude.length == 0) {
            return true;
        }
        return Arrays.stream(exclude).anyMatch(permissions::contains);
    }


    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {
        // 将有权限控制注解的字段单独处理
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

        private final BeanPropertyWriter propertyWriter;

        private static final NullValueSupplier DEFAULT_VALUE_SUPPLIER = new NullValueSupplier();

        public PermissionControlledJsonSerializer(BeanPropertyWriter propertyWriter) {
            this.propertyWriter = propertyWriter;
        }

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            // 无用户,敏感数据，直接打码
            Subject subject = SecurityUtils.getSubject();
            if (Objects.isNull(subject)) {
                jsonGenerator.writeNull();
                return;
            }
            PermissionControlled controlled = propertyWriter.findAnnotation(PermissionControlled.class);
            String[] include = controlled.include();
            String[] exclude = controlled.exclude();
            UserAuthVO authVO = (UserAuthVO) subject.getSession().getAttribute(SessionKeyConstants.USER_SESSION_KEY);

            // 有权限则写入真实的值
            if (!isExclude(authVO.getPermissions(), exclude) && isInclude(authVO.getPermissions(), include)) {
                jsonGenerator.writeObject(o);
                return;
            }

            // 否则，打马赛克
            Class<? extends DefaultValueSupplier> supplier = controlled.supplier();
            if (NullValueSupplier.class != supplier) { // 配置了默认值提供者
                jsonGenerator.writeObject(BeanUtils.instantiateClass(supplier).supply());
            } else if (!Objects.equals(PermissionControlled.NULL, controlled.defaultValue())) { // 配置了默认值String
                jsonGenerator.writeString(controlled.defaultValue());
            } else if (propertyWriter.getType().isArrayType()) { // 如果数组类型则返回空数组
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            } else if (propertyWriter.getType().isCollectionLikeType()) { // 如果是集合则返回空集合
                jsonGenerator.writeObject(Collections.emptyList());
            } else { // 否则执行默认的默认值提供者
                jsonGenerator.writeObject(DEFAULT_VALUE_SUPPLIER.supply());
            }

        }

    }

}
