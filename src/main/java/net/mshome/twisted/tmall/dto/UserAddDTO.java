package net.mshome.twisted.tmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description 新增用户参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddDTO {

    @NotNull(message = "请输入用户名")
    private String username;
    @NotNull(message = "请输入密码")
    private String password;
    @NotNull(message = "请输入真实姓名")
    private String realName;
    @NotNull(message = "请输入地址")
    private String address;

}
