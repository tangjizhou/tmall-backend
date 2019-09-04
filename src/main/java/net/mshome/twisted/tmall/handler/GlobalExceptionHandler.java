package net.mshome.twisted.tmall.handler;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.common.ResultWrapper;
import net.mshome.twisted.tmall.enumeration.ErrorCode;
import net.mshome.twisted.tmall.exception.TmallException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

/**
 * 天猫系统全局异常拦截
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TmallException.class})
    public ResultWrapper<String> handleTmallException(TmallException e) {
        log.error("global exception handler catch a exception", e);
        return ResultWrapper.<String>builder().errorCode(e.getErrorCode()).message(e.getMessage()).build();
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BindException.class})
    public ResultWrapper<String> handleBindException(BindException e) {
        return ResultWrapper.<String>builder().errorCode(ErrorCode.BAD_REQUEST.getValue())
                .message(e.getBindingResult().toString()).build();
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResultWrapper<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResultWrapper.<String>builder().errorCode(ErrorCode.BAD_REQUEST.getValue())
                .message(e.getMessage()).build();
    }


}
