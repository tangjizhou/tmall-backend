package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author tangjizhou
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CheckIn extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 流程id
     */
    private String processId;

    /**
     * 流程节点id
     */
    private String processNodeId;

    /**
     * 流程节点名称
     */
    private String processNodeName;

    /**
     * 备注
     */
    private String remark;


}
