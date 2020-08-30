package net.mshome.twisted.tmall.service.process;

import net.mshome.twisted.tmall.enumeration.ProcessType;
import net.mshome.twisted.tmall.service.process.model.ProcessStatusPayload;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程状态同步服务
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/8/30
 */
@Service
public class ProcessStatusEventBroadcastService implements ProcessExecutionService {

    @Autowired
    private List<ProcessStatusEventSubscriber> subscribers;

    @Override
    public void execute(DelegateExecution delegateExecution, String param) {
        final String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        final String processId = delegateExecution.getProcessInstanceId();
        final String nodeId = delegateExecution.getCurrentFlowElement().getId();
        final String nodeName = delegateExecution.getCurrentFlowElement().getName();
        final ProcessType processType = ProcessType.valueOf(delegateExecution.getProcessDefinitionId());
        final ProcessStatusPayload deliver = new ProcessStatusPayload(businessKey, processId, nodeId, nodeName);
        subscribers.stream().filter(v -> v.supportsProcessType(processType)).forEach(v -> v.sync(deliver));
    }

}
