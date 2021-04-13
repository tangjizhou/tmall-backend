package net.mshome.twisted.tmall.service.process.checkin.task;

import net.mshome.twisted.tmall.service.process.TaskExecutionService;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务生成，通知审批人服务
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/27
 */
@Service
@Transactional
public class TaskCreateNoticeService implements TaskExecutionService {


    @Override
    public void execute(DelegateTask delegateTask, final String param) {
    }

}
