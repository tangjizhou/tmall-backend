package net.mshome.twisted.tmall.mapper;

import net.mshome.twisted.tmall.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mshome.twisted.tmall.enumeration.DataState;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<String> selectCodesByRoles(@Param("roles") Set<String> roles, @Param("dataState") DataState dataState);

}
