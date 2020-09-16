package net.mshome.twisted.tmall.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.dto.ProductSheetModel;
import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.exception.TmallException;
import net.mshome.twisted.tmall.service.IProductService;
import net.mshome.twisted.tmall.service.excel.ProductExportHandler;
import net.mshome.twisted.tmall.service.excel.ProductImportListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation("查询产品")
    @GetMapping("/list")
    public IPage<Product> listProducts(@RequestParam(required = false) String search) {
        IPage<Product> page = new Page<>(1, 10, 10);
        return productService.page(page, new QueryWrapper<Product>()
                .like(StringUtils.isNotBlank(search), "name", search));
    }

    @ApiOperation("导入excel文件")
    @PostMapping("/import")
    private List<String> importProducts(MultipartFile excel) {
        Assert.notNull(excel, "请选择需要上传的Excel文件");
        try (InputStream inputStream = excel.getInputStream()) {
            ProductImportListener importListener = new ProductImportListener(productService);
            EasyExcel.read(inputStream, ProductSheetModel.class, importListener).sheet(0).autoTrim(true).doRead();
            return importListener.getErrors().size() == 0 ? List.of("导入成功") : importListener.getErrors();
        } catch (Exception e) {
            log.error("导入文件失败", e);
            throw new TmallException("导入文件失败");
        }
    }


    @ApiOperation("导出excel文件")
    @PostMapping("/export")
    private void exportProducts(@ApiIgnore HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            String fileName = URLEncoder.encode("产品.xlsx", StandardCharsets.UTF_8.displayName());
            response.setContentType("application/octet-stream");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=".concat(fileName));
            List<ProductSheetModel> productSheetModels = productService.list().stream().map(ProductSheetModel::from)
                    .collect(Collectors.toList());
            ProductExportHandler exportHandler = new ProductExportHandler(productSheetModels.size());
            EasyExcel.write(outputStream, ProductSheetModel.class).registerWriteHandler(exportHandler)
                    .excelType(ExcelTypeEnum.XLSX).sheet("产品").doWrite(productSheetModels);
        } catch (Exception e) {
            log.error("导出文件失败", e);
            throw new TmallException("导出文件失败");
        }
    }

}

