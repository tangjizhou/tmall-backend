package net.mshome.twisted.tmall.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品入库参数
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/8/20
 */
@Data
public class CheckInDTO {

    @NotBlank(message = "请输入商品名称")
    private String productName;
    @NotNull(message = "请选择商品类别")
    private Long categoryId;
    @NotBlank(message = "请输入商品简介")
    private String briefIntroduction;
    private String detailIntroduction;
    @NotNull(message = "请输入商品价格")
    private BigDecimal originalPrice;
    private BigDecimal promotePrice;
    private String remark;

}
