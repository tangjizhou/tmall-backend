package net.mshome.twisted.tmall.service.process;

import net.mshome.twisted.tmall.enumeration.ProcessType;
import net.mshome.twisted.tmall.service.process.model.ProcessStatusPayload;

/**
 * 流程状态同步
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/8/30
 */
public interface ProcessStatusEventSubscriber {

    boolean supportsProcessType(ProcessType processType);

    void sync(ProcessStatusPayload payload);

}
