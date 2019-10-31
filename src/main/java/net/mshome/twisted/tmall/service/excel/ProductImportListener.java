package net.mshome.twisted.tmall.service.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.service.IProductService;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品数据导入
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/10/31
 */
@Slf4j
public class ProductImportListener extends AnalysisEventListener<Product> {

    private List<Product> products;
    private IProductService productService;
    private List<String> errors;

    public ProductImportListener(IProductService productService) {
        this.productService = productService;
        this.products = new ArrayList<>();
        this.errors = new ArrayList<>(0);
    }

    @Override
    public void invoke(Product product, AnalysisContext analysisContext) {
        this.products.add(product);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        String errorMsgPrefix = "第[%d]行";
        List<Product> importProducts = new ArrayList<>(products.size() >> 1);
        for (int i = 0, size = products.size(); i < size; ++i) {
            int rowIndex = i + 2;
            Product product = products.get(i);
            // 校验数据合法性
            List<String> cellErrorMsgList = new ArrayList<>(List.of(errorMsgPrefix));
            addCellErrorMsg(cellErrorMsgList, StringUtils.isBlank(product.getName()), "产品名称不能为空");
            addCellErrorMsg(cellErrorMsgList, BigDecimal.ZERO.equals(product.getPromotePrice()), "产品价格不能为0");
            String rowErrorMsg = String.join(",", cellErrorMsgList);

            if (rowErrorMsg.equals(errorMsgPrefix)) {
                importProducts.add(product);
                continue;
            }
            this.errors.add(String.format(rowErrorMsg, rowIndex));
        }
        log.info("成功导入[{}]产品数据", importProducts.size());
        productService.saveBatch(importProducts);
    }

    private void addCellErrorMsg(List<String> rowCellErrorMsgList, boolean condition, String errorMsg) {
        if (condition) {
            return;
        }
        rowCellErrorMsgList.add(errorMsg);
    }

    public List<String> getErrors() {
        return errors;
    }

}
