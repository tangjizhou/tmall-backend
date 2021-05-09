package net.mshome.twisted.tmall.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * 权限控制注解，根据权限给字段打马赛克
 *
 * @author tangjizhou
 * @date 2020/3/2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD})
public @interface PermissionControlled {

    String NULL = "null";

    /**
     * 可访问的权限集合
     */
    String[] include() default {};

    /**
     * 不可访问的权限集合
     */

    String[] exclude() default {};

    /**
     * 针对大多数字段的默认值，接受一个String类型参数，优先级较{@link #supplier()}低
     */
    String defaultValue() default NULL;

    /**
     * 自定义默认值提供者，优先级较{@link #defaultValue()}高
     */
    Class<? extends DefaultValueSupplier> supplier() default NullValueSupplier.class;

}
