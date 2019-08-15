package net.mshome.twisted.tmall.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 * @description TODO
 */
public class UsersFieldConstraint implements ConstraintValidator {

    @Override
    public void initialize(Annotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
