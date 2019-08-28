package net.mshome.twisted.tmall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.mshome.twisted.tmall.common.Result;
import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public Result<IPage<Product>> listProducts() {
        IPage<Product> page = new Page<>(1, 10,10);
        return Result.<IPage<Product>>builder().result(productService.page(page)).build();
    }

}

