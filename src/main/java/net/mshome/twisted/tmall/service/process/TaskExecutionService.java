package net.mshome.twisted.tmall.service.process;

import org.activiti.engine.delegate.DelegateTask;

/**
 * 自定义流程任务服务接口
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/26
 */
public interface TaskExecutionService {

    void execute(DelegateTask delegateTask, final String param);

}
