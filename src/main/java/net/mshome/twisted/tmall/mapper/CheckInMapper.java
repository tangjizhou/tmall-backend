package net.mshome.twisted.tmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mshome.twisted.tmall.dto.CheckInDTO;
import net.mshome.twisted.tmall.entity.CheckIn;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020-06-04
 */
public interface CheckInMapper extends BaseMapper<CheckIn> {

    LocalDateTime update(@Param("checkInDTO") CheckInDTO checkInDTO);

}
