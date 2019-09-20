package net.mshome.twisted.tmall.configuration;

import io.swagger.models.HttpMethod;
import net.mshome.twisted.tmall.handler.ReturnValueHandler;
import net.mshome.twisted.tmall.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 拦截器配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/28
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/use/logout")
                .excludePathPatterns("swagger-ui.html", "/v2/api-docs", "/webjars/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/**");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name())
                .allowCredentials(true).maxAge(3600);
        super.addCorsMappings(registry);
    }


}