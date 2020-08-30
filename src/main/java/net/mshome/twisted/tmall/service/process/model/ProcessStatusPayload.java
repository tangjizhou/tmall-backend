package net.mshome.twisted.tmall.service.process.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 流程状态交付字段
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/8/30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStatusPayload {

    private String businessKey;
    private String processId;
    private String nodeId;
    private String nodeName;

}
