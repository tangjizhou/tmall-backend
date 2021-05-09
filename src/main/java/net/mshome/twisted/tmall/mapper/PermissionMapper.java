package net.mshome.twisted.tmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mshome.twisted.tmall.entity.Permission;
import net.mshome.twisted.tmall.enumeration.DataState;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<String> selectCodeByRoles(@Param("roleIds") List<Long> roleIds, @Param("dataState") DataState dataState);

}
