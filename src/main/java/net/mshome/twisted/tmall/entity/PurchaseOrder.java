package net.mshome.twisted.tmall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Integer userId;

    private BigDecimal totalPrice;

    /**
     * 0:取消,10:已创建,20:已付款,30:已发货,40:运送中,50:派送中,60:已完成
     */
    private Integer state;

    private String address;

    private String remark;

    private Integer payNum;

    private LocalDateTime payTime;

    private LocalDateTime shipTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
