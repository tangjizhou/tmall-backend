package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.entity.PurchaseOrderItem;
import net.mshome.twisted.tmall.mapper.PurchaseOrderItemMapper;
import net.mshome.twisted.tmall.service.IPurchaseOrderItemService;
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
public class PurchaseOrderItemServiceImpl extends ServiceImpl<PurchaseOrderItemMapper, PurchaseOrderItem> implements IPurchaseOrderItemService {

}
