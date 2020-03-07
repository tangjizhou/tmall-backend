package net.mshome.twisted.tmall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.dto.UserQueryDTO;
import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.vo.UserQueryVO;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
public interface IUserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAddDTO 注册参数
     */
    void register(UserAddDTO userAddDTO);

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return 用户对角色
     */
    List<Role> listUserRoles(Long userId);

    Optional<User> getByUsername(String username);

    Page<UserQueryVO> listByExample(UserQueryDTO queryDTO);

}
