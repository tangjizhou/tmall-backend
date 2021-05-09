package net.mshome.twisted.tmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mshome.twisted.tmall.dto.CheckInDTO;
import net.mshome.twisted.tmall.entity.CheckIn;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangjizhou
 * @since 2020-06-04
 */
public interface CheckInMapper extends BaseMapper<CheckIn> {

    String update(@Param("checkInDTO") CheckInDTO checkInDTO);

}
