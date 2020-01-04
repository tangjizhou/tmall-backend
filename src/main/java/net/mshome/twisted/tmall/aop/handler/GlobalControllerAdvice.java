package net.mshome.twisted.tmall.aop.handler;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.common.ResultWrapper;
import net.mshome.twisted.tmall.enumeration.ErrorCode;
import net.mshome.twisted.tmall.exception.TmallException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 天猫系统全局异常拦截
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 */
@Slf4j
@ResponseBody
@ControllerAdvice
@ResponseStatus(value = HttpStatus.OK)
public class GlobalControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_DATE));
            }
        });
        webDataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        });
    }


    @ExceptionHandler({TmallException.class})
    public ResultWrapper<String> handleTmallException(TmallException e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(e.getErrorCode()).message(e.getMessage()).build();
    }


    @ExceptionHandler({BindException.class})
    public ResultWrapper<String> handleBindException(BindException e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(ErrorCode.BAD_REQUEST.getValue())
                .message(e.getBindingResult().toString()).build();
    }

    @ExceptionHandler({IllegalArgumentException.class, UnknownAccountException.class, UnauthenticatedException.class})
    public ResultWrapper<String> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(ErrorCode.BAD_REQUEST.getValue())
                .message(e.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResultWrapper<String> handleException(Exception e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(ErrorCode.SERVER_INTERNAL_ERROR.getValue())
                .message("服务器异常").build();
    }

}
