package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.entity.PurchaseOrder;
import net.mshome.twisted.tmall.mapper.PurchaseOrderMapper;
import net.mshome.twisted.tmall.service.IPurchaseOrderService;
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
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements IPurchaseOrderService {

}
