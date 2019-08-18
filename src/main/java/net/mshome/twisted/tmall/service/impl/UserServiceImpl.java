package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.enumeration.UserStateEnum;
import net.mshome.twisted.tmall.exception.TmallException;
import net.mshome.twisted.tmall.mapper.UserMapper;
import net.mshome.twisted.tmall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserAddDTO userAddDTO) {
        if (userMapper.selectCount(new QueryWrapper<>(User.builder().username(userAddDTO.getUsername()).build())) > 0) {
            throw new TmallException("当前用户已存在");
        }

        User user = User.builder().username(userAddDTO.getUsername()).password(userAddDTO.getPassword())
                .realName(userAddDTO.getRealName()).address(userAddDTO.getAddress()).state(UserStateEnum.VALID)
                .createTime(LocalDateTime.now()).updateTime(LocalDateTime.now()).build();
        userMapper.insert(user);
    }
}
