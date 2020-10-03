package net.mshome.twisted.tmall.controller;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.constant.SessionKeyConstants;
import net.mshome.twisted.tmall.dto.UserLoginDTO;
import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IPermissionService;
import net.mshome.twisted.tmall.service.IRoleService;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.util.EntityUtils;
import net.mshome.twisted.tmall.vo.UserAuthVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 登入登出
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/4
 */
@Validated
@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;


    @PostMapping("/login")
    public UserAuthVO login(@Validated @RequestBody UserLoginDTO loginDTO, @ApiIgnore HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (IncorrectCredentialsException e) {
            throw new UnauthenticatedException("登陆失败。账号密码错误");
        } catch (UnknownAccountException e) {
            throw new UnauthenticatedException("登录失败。用户名不存在");
        } catch (AuthenticationException e) {
            throw new UnauthenticatedException("登录失败");
        }
        if (!subject.isAuthenticated()) {
            throw new UnauthenticatedException("登录失败");
        }
        Optional<User> userOptional = userService.getByUsername(username);
        Long userId = userOptional.map(User::getId).orElse(0L);
        String realName = userOptional.map(User::getRealName).orElse("游客");
        List<Role> roles = roleService.listByUserId(userId);
        Set<String> roleCodes = EntityUtils.collectToSet(roles, Role::getCode);
        List<Long> roleIds = EntityUtils.collectIds(roles);
        Set<String> permissions = permissionService.listCodeByRoleIds(roleIds);
        UserAuthVO userAuthVO = UserAuthVO.builder().username(username).realName(realName)
                .roles(roleCodes).permissions(permissions).build();
        session.setAttribute(SessionKeyConstants.USER_SESSION_KEY, userAuthVO);
        return userAuthVO;
    }

    @PostMapping("/logout")
    public void logout(@ApiIgnore HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        request.getSession().invalidate();
    }

}
