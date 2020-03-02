package net.mshome.twisted.tmall.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD})
public @interface PermissionControlled {

    String[] include() default {};

    String[] exclude() default {};

    String defaultValue() default "";


}
