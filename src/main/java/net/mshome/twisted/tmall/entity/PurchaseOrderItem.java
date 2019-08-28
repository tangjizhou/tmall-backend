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
public class PurchaseOrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Integer orderId;

    private Integer productId;

    private String productName;

    private Integer productCount;

    private BigDecimal originalPrice;

    private BigDecimal dealPrice;

    /**
     * 0:交易失败,1:交易成功
     */
    private Integer state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
