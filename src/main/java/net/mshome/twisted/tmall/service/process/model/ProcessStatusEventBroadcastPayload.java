package net.mshome.twisted.tmall.service.process.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 流程状态交付字段
 *
 * @author tangjizhou
 * @since 2020/8/30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStatusEventBroadcastPayload {

    private String businessKey;
    private String processId;
    private String nodeId;
    private String nodeName;

}
