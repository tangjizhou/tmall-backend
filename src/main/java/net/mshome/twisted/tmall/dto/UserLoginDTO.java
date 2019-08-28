package net.mshome.twisted.tmall.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/28
 * @description 用户登陆参数
 */
public class UserLoginDTO {

    @NotBlank(message = "请输入用户名")
    private String username;
    @NotBlank(message = "请输入密码s")
    private String password;

}
