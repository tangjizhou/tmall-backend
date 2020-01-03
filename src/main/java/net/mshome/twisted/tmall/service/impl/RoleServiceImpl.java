package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.mapper.RoleMapper;
import net.mshome.twisted.tmall.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public Set<String> listCodesByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Collections.emptySet();
        }
        return baseMapper.selectCodesByUsername(userId, DataState.VALID);
    }

}
