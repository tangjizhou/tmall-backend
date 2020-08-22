package net.mshome.twisted.tmall.service.process;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * 流程流转级别服务
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/26
 */
public interface ProcessExecutionService {

    void execute(DelegateExecution delegateExecution, final String param);

}
