package net.mshome.twisted.tmall.service.process.checkin.execution;

import net.mshome.twisted.tmall.entity.CheckIn;
import net.mshome.twisted.tmall.service.ICheckInService;
import net.mshome.twisted.tmall.service.process.ProcessExecutionService;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 更新流程状态到业务表
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/6/4
 */
@Service("CHECK_IN_ProcessStatusSynchronizer")
@Transactional
public class ProcessStatusSynchronizer implements ProcessExecutionService {

    @Autowired
    private ICheckInService checkInService;

    @Override
    public void execute(DelegateExecution delegateExecution, String param) {
        FlowElement flowElement = delegateExecution.getCurrentFlowElement();
        CheckIn checkIn = checkInService.getById(delegateExecution.getProcessInstanceBusinessKey());
        checkIn.setProcessNodeId(flowElement.getId());
        checkIn.setProcessNodeName(flowElement.getName());
        checkInService.updateById(checkIn);
    }

}
