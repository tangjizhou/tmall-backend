package net.mshome.twisted.tmall.aop.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 包装返回值,针对有mapping注解的方法返回的json数据包装
 * 修改处理返回结果handler链表，在中间添加自定义handler
 *
 * @author tangjizhou
 * @date 2019/9/4
 */
@Component
public class ReturnValueHandler extends RequestResponseBodyMethodProcessor {

    public ReturnValueHandler(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Method method = returnType.getMethod();
        if (method == null || method.getReturnType().isAssignableFrom(ResultWrapper.class)
                || method.getReturnType().isAssignableFrom(Exception.class)) {
            return false;
        }

        return super.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        returnValue = ResultWrapper.builder().result(returnValue).build();
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

}
