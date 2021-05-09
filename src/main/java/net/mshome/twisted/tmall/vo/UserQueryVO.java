package net.mshome.twisted.tmall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mshome.twisted.tmall.annotation.PermissionControlled;
import net.mshome.twisted.tmall.constant.PermissionConstants;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.enumeration.DataState;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户数据前台展示
 *
 * @author tangjizhou
 * @date 2019/9/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryVO implements Serializable {

    private static final long serialVersionUID = 4346366700132759635L;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private DataState dataState;
    @PermissionControlled(include = PermissionConstants.USER_MANAGE, defaultValue = "--")
    private String realName;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;

    public static UserQueryVO fromUser(User user) {
        UserQueryVO vo = new UserQueryVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }


}
