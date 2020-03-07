package net.mshome.twisted.tmall.annotation;

import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.util.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户字段校验器
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 */
public class UserFieldValidator implements ConstraintValidator<UsersField, String> {


    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String usernames, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(usernames)) {
            return true;
        }

        List<String> usernameList = List.of(usernames.split(","));
        List<User> users = userService.listByUsernames(usernameList);
        Map<String, User> userMap = EntityUtils.indexElement(users, User::getUsername);

        String invalidUsers = usernameList.stream()
                .map(username -> Objects.isNull(userMap.get(usernames)) ? username : null)
                .filter(Objects::nonNull).collect(Collectors.joining(","));

        if (StringUtils.isBlank(invalidUsers)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        String messageTemplate = context.getDefaultConstraintMessageTemplate();
        messageTemplate = StringUtils.isBlank(messageTemplate) ? UsersField.DEFAULT_MESSAGE : messageTemplate;
        context.buildConstraintViolationWithTemplate(StringUtils.replace(messageTemplate, "{}", invalidUsers))
                .addConstraintViolation();
        return false;
    }

}
