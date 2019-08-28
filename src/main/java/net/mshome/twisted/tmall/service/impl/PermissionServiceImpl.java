package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.entity.Permission;
import net.mshome.twisted.tmall.mapper.PermissionMapper;
import net.mshome.twisted.tmall.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
