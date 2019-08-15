package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.mapper.ProductMapper;
import net.mshome.twisted.tmall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
