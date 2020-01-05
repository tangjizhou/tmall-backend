package net.mshome.twisted.tmall.mapper;

import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mshome.twisted.tmall.vo.UserQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 更新密码
     *
     * @param username    用户名
     * @param newPassword 新密码
     */
    void updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    /**
     * 根据用户id查询用户权限
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> listUserRoles(Long userId);

    /**
     * 查询用户列表
     *
     * @param username 账号
     * @param realName 姓名
     * @param state    状态
     * @return 用户信息
     */
    List<UserQueryVO> listAll(@Param("username") String username, @Param("realName") String realName,
                              @Param("state") String state);

}
