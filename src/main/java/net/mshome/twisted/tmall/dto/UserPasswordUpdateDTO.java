package net.mshome.twisted.tmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改密码请求参数
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateDTO {

    @NotNull(message = "未获取到当前用户")
    String username;
    @NotBlank(message = "原密码不能为空")
    String oldPassword;
    @NotBlank(message = "新密码不能为空")
    String newPassword;
    @NotBlank(message = "请再次输入新密码")
    String reNewPassword;

}
