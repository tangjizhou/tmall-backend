package net.mshome.twisted.tmall.service;

import net.mshome.twisted.tmall.enumeration.ProcessType;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * 流程服务类
 *
 * @author tangjizhou
 * @since 2020/8/20
 */
public interface IProcessService {

    ProcessInstance startProcess(ProcessType processKey, String businessKey, Map<String, Object> vars);

}
