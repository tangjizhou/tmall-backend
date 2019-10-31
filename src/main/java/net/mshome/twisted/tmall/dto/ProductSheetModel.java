package net.mshome.twisted.tmall.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mshome.twisted.tmall.entity.Product;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 产品excel导入
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/10/31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(20)
@HeadRowHeight(30)
public class ProductSheetModel {

    @ColumnWidth(0)
    @ExcelProperty(value = "ID", index = 0)
    private Long id;

    @ExcelProperty(value = "名称", index = 1)
    private String name;

    @ExcelProperty(value = "简要描述", index = 2)
    private String briefIntroduction;

    @ExcelProperty(value = "详细描述", index = 3)
    private String detailIntroduction;

    @ExcelProperty(value = "原价", index = 4)
    private BigDecimal originalPrice;

    @ExcelProperty(value = "促销价", index = 5)
    private BigDecimal promotePrice;

    public static ProductSheetModel from(Product product) {
        ProductSheetModel sheetModel = new ProductSheetModel();
        BeanUtils.copyProperties(product, sheetModel);
        return sheetModel;
    }


    public Product toProduct() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }

}
