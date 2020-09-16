package net.mshome.twisted.tmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mshome.twisted.tmall.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
public interface IPermissionService extends IService<Permission> {

    Set<String> listCodeByRoleIds(List<Long> roleIds);

}
