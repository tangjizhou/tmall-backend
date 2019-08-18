package net.mshome.twisted.tmall.handler;

import net.mshome.twisted.tmall.common.Result;
import net.mshome.twisted.tmall.exception.TmallException;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description 天猫系统全局异常拦截
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TmallException.class)
    public Result<String> handleTmallException(HttpServletRequest request, TmallException e) {
        return Result.<String>builder().success(false).message(e.getMessage()).build();
    }

}
