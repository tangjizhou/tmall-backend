package net.mshome.twisted.tmall.service;

import net.mshome.twisted.tmall.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

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

    Set<String> listCodesByRoles(Set<String> roles);

}
