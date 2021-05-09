package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private BigDecimal totalPrice;

    private String address;

    private String remark;

    private Integer payNum;

    private LocalDateTime payTime;

    private LocalDateTime shipTime;

}
