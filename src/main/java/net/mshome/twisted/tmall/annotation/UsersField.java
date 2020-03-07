package net.mshome.twisted.tmall.annotation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 用户字段校验注解
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = UserFieldValidator.class)
public @interface UsersField {

    String message() default "";

    String DEFAULT_MESSAGE = "用户{}不存在";

}

