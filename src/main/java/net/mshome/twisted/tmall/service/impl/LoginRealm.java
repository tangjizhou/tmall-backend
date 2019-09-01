package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/28
 * @description TODO
 */
@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.getOne(new QueryWrapper<>(User.builder().username(username).build()));
        if (user == null) {
            throw new UnauthenticatedException("账号密码错误");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        List<Role> roleList = userService.listUserRoles(user.getId());

        authorizationInfo.addRoles(roleList.stream().map(Role::getRoleName)
                .collect(Collectors.toCollection(ArrayList::new)));
        //authorizationInfo.addStringPermissions();


        return null;
    }

}
