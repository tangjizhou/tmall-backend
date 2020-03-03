package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.mshome.twisted.tmall.annotation.PermissionControlled;
import net.mshome.twisted.tmall.annotation.ProductNameDefaultValueSupplier;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品实体类
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @PermissionControlled(include = "admin", exclude = "user", supplier = ProductNameDefaultValueSupplier.class)
    private String name;

    private Integer categoryId;

    private String briefIntroduction;

    private String detailIntroduction;

    private BigDecimal originalPrice;

    private BigDecimal promotePrice;

}
