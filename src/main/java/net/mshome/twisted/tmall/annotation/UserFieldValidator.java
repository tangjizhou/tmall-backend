package net.mshome.twisted.tmall.annotation;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户字段校验器
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 */
public class UserFieldValidator implements ConstraintValidator<UsersField, String> {

    private static String DEFAULT_MESSAGE;

    @Autowired
    private IUserService userService;

    @Override
    public void initialize(UsersField constraintAnnotation) {
        DEFAULT_MESSAGE = UsersField.DEFAULT_MESSAGE;
    }

    @Override
    public boolean isValid(String usernames, ConstraintValidatorContext context) {
        if (StrUtil.isBlank(usernames)) {
            return true;
        }

        String invalidUsers = Arrays.stream(usernames.split(",")).parallel().map(username -> {
            int count = userService.count(new QueryWrapper<>(User.builder().username(username).build()));
            return count == 0 ? username : null;
        }).filter(Objects::nonNull).collect(Collectors.joining(","));

        if (StrUtil.isBlank(invalidUsers)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        String messageTemplate = context.getDefaultConstraintMessageTemplate();
        messageTemplate = StrUtil.isBlank(messageTemplate) ? DEFAULT_MESSAGE : messageTemplate;
        context.buildConstraintViolationWithTemplate(StrUtil.replace(messageTemplate, "{}", invalidUsers))
                .addConstraintViolation();
        return false;
    }

}
