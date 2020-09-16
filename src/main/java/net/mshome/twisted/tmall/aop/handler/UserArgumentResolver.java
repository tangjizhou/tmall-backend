package net.mshome.twisted.tmall.aop.handler;

import net.mshome.twisted.tmall.constant.SessionConstants;
import net.mshome.twisted.tmall.vo.UserAuthVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义用户参数注入
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/9/16
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.getDeclaringClass().isAnnotationPresent(Controller.class)
                || methodParameter.getDeclaringClass().isAnnotationPresent(RestController.class)) &&
                methodParameter.getParameterType().isAssignableFrom(UserAuthVO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
            throws Exception {
        return SecurityUtils.getSubject().getSession().getAttribute(SessionConstants.USER_SESSION_KEY);
    }

}
