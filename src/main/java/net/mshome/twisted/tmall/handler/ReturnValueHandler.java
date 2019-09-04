package net.mshome.twisted.tmall.handler;

import net.mshome.twisted.tmall.common.ResultWrapper;
import net.mshome.twisted.tmall.exception.TmallException;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * 包装返回值,针对有mapping注解的方法返回值包装
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/4
 */
@Component
public class ReturnValueHandler extends RequestResponseBodyMethodProcessor {

    public ReturnValueHandler(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        List<Class<? extends Annotation>> annotationList = List.of(GetMapping.class, PostMapping.class,
                RequestMapping.class, PutMapping.class, DeleteMapping.class);
        Annotation[] annotations = returnType.getMethodAnnotations();
        return Arrays.stream(annotations).anyMatch(annotation -> annotationList.contains(annotation.annotationType()));
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        if (returnValue instanceof TmallException) {
            TmallException exception = (TmallException) returnValue;
            returnValue = ResultWrapper.builder().result(returnType).errorCode(exception.getErrorCode()).message(exception.getMessage()).build();
        }
        returnValue = ResultWrapper.builder().result(returnValue).build();
        mavContainer.setRequestHandled(true);
        ServletServerHttpRequest inputMessage = createInputMessage(webRequest);
        ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);
        writeWithMessageConverters(returnValue, returnType, inputMessage, outputMessage);
    }

}
