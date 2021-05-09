package net.mshome.twisted.tmall.aop.interceptor;

import net.mshome.twisted.tmall.constant.SessionKeyConstants;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 用户登陆拦截
 *
 * @author tangjizhou
 * @date 2019-08-18
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        if (Objects.isNull(session.getAttribute(SessionKeyConstants.USER_SESSION_KEY))) {
            throw new UnauthenticatedException("用户未登陆或登陆过期");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
