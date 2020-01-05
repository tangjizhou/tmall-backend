package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.mapper.UserMapper;
import net.mshome.twisted.tmall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.vo.UserQueryVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordMatcher passwordMatcher;

    @Override
    public void register(UserAddDTO userAddDTO) {
        String username = userAddDTO.getUsername();
        String password = passwordMatcher.getPasswordService().encryptPassword(userAddDTO.getPassword());
        int count = baseMapper.selectCount(new QueryWrapper<>(User.builder().username(username).build()));
        Preconditions.checkArgument(count == 0, "用户%s已存在", username);
        User user = User.builder().username(username).password(password)
                .address(userAddDTO.getAddress()).realName(userAddDTO.getRealName()).build();
        baseMapper.insert(user);
    }

    @Override
    public List<Role> listUserRoles(Long userId) {
        if (userId == null || userId == 0) {
            return Collections.emptyList();
        }
        return baseMapper.listUserRoles(userId);
    }

    @Override
    public List<UserQueryVO> listAll(String username, String realName, DataState dataState) {
        return baseMapper.listAll(username, realName, dataState == null ? null : dataState.getValue());
    }

    @Override
    public Optional<User> getByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return Optional.empty();
        }
        var where = new QueryWrapper<>(User.builder().username(username).build());
        return Optional.ofNullable(getOne(where));
    }

}
