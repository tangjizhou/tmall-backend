package net.mshome.twisted.tmall.handler;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.common.Result;
import net.mshome.twisted.tmall.exception.TmallException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description 天猫系统全局异常拦截
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TmallException.class, IllegalArgumentException.class})
    public Result<String> handleTmallException(RuntimeException e) {
        log.error("global exception handler catch a exception", e);
        return Result.<String>builder().success(false).message(e.getMessage()).build();
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    public Result<String> handleBindException(BindException e) {
        return Result.<String>builder().success(false).message(e.getBindingResult().toString()).build();
    }


}
