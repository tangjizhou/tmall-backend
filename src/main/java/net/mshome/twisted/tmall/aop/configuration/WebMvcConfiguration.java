package net.mshome.twisted.tmall.aop.configuration;

import net.mshome.twisted.tmall.aop.handler.ReturnValueHandler;
import net.mshome.twisted.tmall.aop.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * 配置类，配置自定义返回值处理器
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/4
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * 自定义的返回值处理器
     */
    @Autowired
    private ReturnValueHandler returnValueHandler;

    @PostConstruct
    public void init() {
        final List<HandlerMethodReturnValueHandler> newHandlers = new LinkedList<>();
        final List<HandlerMethodReturnValueHandler> originalHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        if (null != originalHandlers) {
            newHandlers.addAll(originalHandlers);
            final int index = obtainValueHandlerPosition(originalHandlers);
            newHandlers.add(index, returnValueHandler);
        } else {
            newHandlers.add(returnValueHandler);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    /**
     * 获取让自定义处理器生效应处于的位置
     *
     * @param originalHandlers 已经添加的返回值处理器
     * @return 自定义处理器需要的位置
     */
    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (RequestResponseBodyMethodProcessor.class.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        //         .excludePathPatterns("/auth/login", "/auth/logout")
        //         .excludePathPatterns("**/swagger-ui.html", "/v2/api-docs", "/webjars/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/**");
        super.addResourceHandlers(registry);
    }

}
