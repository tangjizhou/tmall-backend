package net.mshome.twisted.tmall.controller;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.constant.SessionConstants;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IPermissionService;
import net.mshome.twisted.tmall.service.IRoleService;
import net.mshome.twisted.tmall.service.IUserService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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


    @GetMapping("/login")
    public UserAuthVO login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
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
        String realName = userOptional.map(User::getRealName).orElse("小白");
        Set<String> roles = roleService.listCodesByUserId(userId);
        Set<String> permissions = permissionService.listCodesByRoles(roles);
        UserAuthVO userAuthVO = UserAuthVO.builder().username(username).realName(realName)
                .roles(roles).permissions(permissions).build();
        session.setAttribute(SessionConstants.USER_SESSION_KEY, userAuthVO);
        return userAuthVO;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        request.getSession().invalidate();
    }

}
