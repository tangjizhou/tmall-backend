package net.mshome.twisted.tmall.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登陆参数
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/4/6
 */
@Data
public class UserLoginDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;

}
