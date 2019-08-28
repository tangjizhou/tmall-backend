package net.mshome.twisted.tmall.mapper;

import net.mshome.twisted.tmall.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
