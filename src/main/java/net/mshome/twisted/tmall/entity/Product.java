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
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 名称
     */
    private String name;

    private Integer categoryId;

    /**
     * 0:无效,1:有效,9:封杀
     */
    private Integer state;

    private String briefIntroduction;

    private String detailIntroduction;

    private BigDecimal originalPrice;

    private BigDecimal promotePrice;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
