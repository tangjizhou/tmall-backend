package net.mshome.twisted.tmall.handler;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.common.ResultWrapper;
import net.mshome.twisted.tmall.enumeration.ErrorCode;
import net.mshome.twisted.tmall.exception.TmallException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 天猫系统全局异常拦截
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 */
@Slf4j
@ControllerAdvice
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler({TmallException.class})
    public ResultWrapper<String> handleTmallException(TmallException e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(e.getErrorCode()).message(e.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler({BindException.class})
    public ResultWrapper<String> handleBindException(BindException e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(ErrorCode.BAD_REQUEST.getValue())
                .message(e.getBindingResult().toString()).build();
    }

    @ResponseBody
    @ExceptionHandler({IllegalArgumentException.class})
    public ResultWrapper<String> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(ErrorCode.BAD_REQUEST.getValue())
                .message(e.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultWrapper<String> handleException(Exception e, HttpServletRequest request) {
        log.error(String.format("%s,url [%s]", e.getMessage(), request.getRequestURL()), e);
        return ResultWrapper.<String>builder().code(ErrorCode.SERVER_INTERNAL_ERROR.getValue())
                .message("服务器异常").build();
    }

}
