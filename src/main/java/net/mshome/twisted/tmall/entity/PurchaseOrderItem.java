package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class PurchaseOrderItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;


    private Integer orderId;

    private Integer productId;

    private String productName;

    private Integer productCount;

    private BigDecimal originalPrice;

    private BigDecimal dealPrice;

}
