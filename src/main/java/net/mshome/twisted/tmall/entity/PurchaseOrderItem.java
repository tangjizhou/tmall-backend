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
public class PurchaseOrderItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;


    private Integer orderId;

    private Integer productId;

    private String productName;

    private Integer productCount;

    private BigDecimal originalPrice;

    private BigDecimal dealPrice;

}
