package net.mshome.twisted.tmall.service;

import net.mshome.twisted.tmall.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
public interface IRoleService extends IService<Role> {

    Set<String> listCodesByUserId(Long userId);

}
