package net.mshome.twisted.tmall.entity;

import net.mshome.twisted.tmall.annotation.PermissionControlled;

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
// @Data
// @EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @PermissionControlled(include = "test")
    private String name;

    private Integer categoryId;

    private String briefIntroduction;

    private String detailIntroduction;

    private BigDecimal originalPrice;

    private BigDecimal promotePrice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getDetailIntroduction() {
        return detailIntroduction;
    }

    public void setDetailIntroduction(String detailIntroduction) {
        this.detailIntroduction = detailIntroduction;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(BigDecimal promotePrice) {
        this.promotePrice = promotePrice;
    }

}
