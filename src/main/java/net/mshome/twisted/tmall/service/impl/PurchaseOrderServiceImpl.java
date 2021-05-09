package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.entity.PurchaseOrder;
import net.mshome.twisted.tmall.enumeration.ProcessType;
import net.mshome.twisted.tmall.mapper.PurchaseOrderMapper;
import net.mshome.twisted.tmall.service.IPurchaseOrderService;
import net.mshome.twisted.tmall.service.process.ProcessStatusEventSubscriber;
import net.mshome.twisted.tmall.service.process.model.ProcessStatusEventBroadcastPayload;
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
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements
        IPurchaseOrderService, ProcessStatusEventSubscriber {

    @Override
    public boolean supportsProcessType(ProcessType processType) {
        return ProcessType.CHECK_OUT == processType;
    }

    @Override
    public void sync(ProcessStatusEventBroadcastPayload payload) {

    }

}
