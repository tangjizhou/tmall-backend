package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.entity.Permission;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.mapper.PermissionMapper;
import net.mshome.twisted.tmall.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Set;

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

    @Override
    public Set<String> listCodesByRoles(Set<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptySet();
        }
        return baseMapper.selectCodesByRoles(roles, DataState.VALID);
    }

}
