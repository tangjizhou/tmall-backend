package net.mshome.twisted.tmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mshome.twisted.tmall.entity.Role;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-28
 */
public interface IRoleService extends IService<Role> {

    List<Role> listByUserId(Long userId);

}
