package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.mapper.ProductMapper;
import net.mshome.twisted.tmall.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-26
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
