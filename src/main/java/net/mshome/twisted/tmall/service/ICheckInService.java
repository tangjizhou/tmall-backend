package net.mshome.twisted.tmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mshome.twisted.tmall.dto.CheckInDTO;
import net.mshome.twisted.tmall.entity.CheckIn;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020-06-04
 */
public interface ICheckInService extends IService<CheckIn> {

    void checkIn(CheckInDTO checkInDTO);

    void update(CheckInDTO checkInDTO);

}
