package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.entity.Permission;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.mapper.PermissionMapper;
import net.mshome.twisted.tmall.service.IPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-28
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public Set<String> listCodeByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        return baseMapper.selectCodeByRoles(roleIds, DataState.VALID);
    }

}
