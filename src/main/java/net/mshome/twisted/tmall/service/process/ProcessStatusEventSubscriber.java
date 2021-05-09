package net.mshome.twisted.tmall.service.process;

import net.mshome.twisted.tmall.enumeration.ProcessType;
import net.mshome.twisted.tmall.service.process.model.ProcessStatusEventBroadcastPayload;

/**
 * 流程状态同步
 *
 * @author tangjizhou
 * @since 2020/8/30
 */
public interface ProcessStatusEventSubscriber {

    boolean supportsProcessType(ProcessType processType);

    void sync(ProcessStatusEventBroadcastPayload payload);

}
