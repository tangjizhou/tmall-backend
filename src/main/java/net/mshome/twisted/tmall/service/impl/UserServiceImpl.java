package net.mshome.twisted.tmall.service.impl;

import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.mapper.UserMapper;
import net.mshome.twisted.tmall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
