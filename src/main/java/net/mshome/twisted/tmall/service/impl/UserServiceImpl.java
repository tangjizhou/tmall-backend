package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.dto.UserQueryDTO;
import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.mapper.UserMapper;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.vo.UserQueryVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (Objects.isNull(userId) || userId == 0) {
            return Collections.emptyList();
        }
        return baseMapper.listUserRoles(userId);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return Optional.empty();
        }
        var where = new QueryWrapper<>(User.builder().username(username).build());
        return Optional.ofNullable(getOne(where));
    }

    @Override
    public Page<UserQueryVO> listByExample(UserQueryDTO queryDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryDTO.getUsername()), "username", queryDTO.getUsername());
        queryWrapper.like(StringUtils.isNotBlank(queryDTO.getRealName()), "real_name", queryDTO.getRealName());
        queryWrapper.eq(Objects.nonNull(queryDTO.getDataState()), "data_state", queryDTO.getDataState());
        IPage<User> usersPage = page(queryDTO.toPage(), queryWrapper);
        Page<UserQueryVO> voPage = queryDTO.toPage();
        BeanUtils.copyProperties(usersPage, voPage);
        return voPage.setRecords(usersPage.getRecords().stream().map(UserQueryVO::fromUser)
                .collect(Collectors.toList()));
    }

    @Override
    public List<User> listByUsernames(List<String> usernames) {
        if (CollectionUtils.isEmpty(usernames)) {
            return Collections.emptyList();
        }
        var queryWrapper = new QueryWrapper<User>().in("username", usernames);
        return list(queryWrapper).stream().distinct().collect(Collectors.toList());
    }

}
