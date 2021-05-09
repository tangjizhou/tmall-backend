package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.enumeration.ProcessType;
import net.mshome.twisted.tmall.service.IProcessService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 流程服务
 *
 * @author tangjizhou
 * @since 2020/8/20
 */
@Service
public class ProcessServiceImpl implements IProcessService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Override
    public ProcessInstance startProcess(ProcessType processKey, String businessKey, Map<String, Object> vars) {
        return runtimeService.startProcessInstanceById(processKey.name(), businessKey, vars);
    }

}
