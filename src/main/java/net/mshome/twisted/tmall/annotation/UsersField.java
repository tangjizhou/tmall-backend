package net.mshome.twisted.tmall.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 * @description 用户字段校验注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = UserFieldValidator.class)
public @interface UsersField {

    String message() default "";

    String defaultMessage = "用户{}不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

