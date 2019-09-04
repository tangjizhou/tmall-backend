package net.mshome.twisted.tmall.configuration;

import net.mshome.twisted.tmall.handler.ReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/4
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    private ReturnValueHandler returnValueHandler;

    @PostConstruct
    public void init() {
        final List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
        final List<HandlerMethodReturnValueHandler> originalHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        if (null != originalHandlers) {
            newHandlers.addAll(originalHandlers);
            final int deferredPos = obtainValueHandlerPosition(originalHandlers, RequestResponseBodyMethodProcessor.class);
            newHandlers.add(deferredPos - 1, returnValueHandler);
        } else {
            newHandlers.add(returnValueHandler);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }

}
