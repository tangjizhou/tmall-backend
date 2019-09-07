package net.mshome.twisted.tmall.exception;

import lombok.Getter;
import net.mshome.twisted.tmall.enumeration.ErrorCode;

/**
 * 天猫系统异常，所有的异常应该继承该异常
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 */
public class TmallException extends RuntimeException {

    private static final long serialVersionUID = 5601214164399834870L;
    @Getter
    private int errorCode = ErrorCode.SERVER_INTERNAL_ERROR.getValue();

    public TmallException(String message) {
        super(message);
    }

    public TmallException(String message, Throwable cause) {
        super(message, cause);
    }

    public TmallException(Throwable cause) {
        super(cause);
    }

}
