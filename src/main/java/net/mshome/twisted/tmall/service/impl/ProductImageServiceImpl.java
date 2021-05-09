package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.entity.ProductImage;
import net.mshome.twisted.tmall.mapper.ProductImageMapper;
import net.mshome.twisted.tmall.service.IProductImageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-26
 */
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements IProductImageService {

}
