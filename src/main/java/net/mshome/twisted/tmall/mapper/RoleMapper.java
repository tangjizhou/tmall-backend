package net.mshome.twisted.tmall.mapper;

import net.mshome.twisted.tmall.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mshome.twisted.tmall.enumeration.DataState;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    Set<String> selectCodesByUsername(@Param("userId") Long userId, @Param("dataState") DataState dataState);

}
