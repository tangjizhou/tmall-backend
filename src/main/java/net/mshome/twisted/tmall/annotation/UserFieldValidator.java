package net.mshome.twisted.tmall.annotation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 * @description 用户字段校验器
 */
public class UserFieldValidator implements ConstraintValidator<UsersField, String> {

    private static String DEFAULT_MESSAGE;

    @Autowired
    private IUserService userService;

    @Override
    public void initialize(UsersField constraintAnnotation) {
        DEFAULT_MESSAGE = UsersField.defaultMessage;
    }

    @Override
    public boolean isValid(String usernames, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(usernames)) {
            return true;
        }

        String invalidUsers = Arrays.stream(usernames.split(",")).parallel().map(username -> {
            int count = userService.count(new QueryWrapper<>(User.builder().username(username).build()));
            return count == 0 ? username : null;
        }).filter(Objects::nonNull).collect(Collectors.joining(","));

        if (StringUtils.isBlank(invalidUsers)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        String messageTemplate = context.getDefaultConstraintMessageTemplate();
        messageTemplate = StringUtils.isBlank(messageTemplate) ? DEFAULT_MESSAGE : messageTemplate;
        context.buildConstraintViolationWithTemplate(StringUtils.replace(messageTemplate, "{}", invalidUsers))
                .addConstraintViolation();
        return false;
    }
}
