package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.exception.TmallException;
import net.mshome.twisted.tmall.mapper.UserMapper;
import net.mshome.twisted.tmall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserAddDTO userAddDTO) {
        String username = userAddDTO.getUsername();
        int count = userMapper.selectCount(new QueryWrapper<>(User.builder().username(username).build()));
        Preconditions.checkArgument(count == 0,"用户{}已存在",username);
        User user = User.builder().username(username).password(userAddDTO.getPassword())
                .address(userAddDTO.getAddress()).realName(userAddDTO.getRealName()).build();
        userMapper.insert(user);
    }

}
