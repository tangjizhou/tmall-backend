package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.dto.CheckInDTO;
import net.mshome.twisted.tmall.entity.CheckIn;
import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.enumeration.ProcessType;
import net.mshome.twisted.tmall.mapper.CheckInMapper;
import net.mshome.twisted.tmall.service.ICheckInService;
import net.mshome.twisted.tmall.service.IProcessService;
import net.mshome.twisted.tmall.service.IProductService;
import net.mshome.twisted.tmall.service.process.ProcessStatusEventSubscriber;
import net.mshome.twisted.tmall.service.process.model.ProcessStatusEventBroadcastPayload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 商品入库服务
 *
 * @author tangjizhou
 * @since 2020-06-04
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements ICheckInService,
        ProcessStatusEventSubscriber {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProcessService processService;

    @Override
    public void checkIn(CheckInDTO checkInDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(checkInDTO, product);
        productService.save(product);
        CheckIn checkIn = new CheckIn();
        checkIn.setId(IdWorker.getId());
        checkIn.setProductId(product.getId());
        checkIn.setRemark(checkInDTO.getRemark());
        save(checkIn);
        processService.startProcess(ProcessType.CHECK_IN, checkIn.getId().toString(), Collections.emptyMap());
    }

    @Override
    public void update(CheckInDTO checkInDTO) {
        System.out.println(baseMapper.update(checkInDTO));
    }

    @Override
    public void sync(ProcessStatusEventBroadcastPayload payload) {
        CheckIn checkIn = new CheckIn();
        checkIn.setId(Long.valueOf(payload.getBusinessKey()));
        checkIn.setProcessNodeId(payload.getNodeId());
        checkIn.setProcessNodeName(payload.getNodeName());
        updateById(checkIn);
    }

    @Override
    public boolean supportsProcessType(ProcessType processType) {
        return ProcessType.CHECK_IN == processType;
    }

}
