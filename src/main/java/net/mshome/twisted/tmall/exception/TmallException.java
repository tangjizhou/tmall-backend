package net.mshome.twisted.tmall.exception;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description 天猫系统异常
 */
public class TmallException extends RuntimeException {

    public TmallException(String message) {
        super(message);
    }

    public TmallException(String message, Throwable cause) {
        super(message, cause);
    }

    public TmallException(Throwable cause) {
        super(cause);
    }

    public TmallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
