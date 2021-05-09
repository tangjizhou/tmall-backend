package net.mshome.twisted.tmall.exception;

/**
 * 流程执行异常
 *
 * @author tangjizhou
 * @since 2020/3/27
 */
public class ProcessExecuteException extends RuntimeException {

    private static final long serialVersionUID = -5839256983432935555L;

    public ProcessExecuteException(String message) {
        super(message);
    }

    public ProcessExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

}
