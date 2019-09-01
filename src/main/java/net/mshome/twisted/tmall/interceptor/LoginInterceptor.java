package net.mshome.twisted.tmall.interceptor;

import net.mshome.twisted.tmall.constant.SessionConstant;
import net.mshome.twisted.tmall.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description 用户登陆拦截
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String currentUrl = request.getRequestURL().toString();
        User user = (User) request.getSession().getAttribute(SessionConstant.USER_SESSION_KEY);
        //if (user == null) {
        //    if (RequestMethod.GET.name().equals(request.getMethod())) {
        //        response.sendRedirect("/user/loginPage?redirect=".concat(currentUrl).concat(request.getQueryString()));
        //        return false;
        //    }
        //    response.sendRedirect("/user/loginPage");
        //    return false;
        //}

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
