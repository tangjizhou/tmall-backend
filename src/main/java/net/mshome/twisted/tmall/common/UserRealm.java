package net.mshome.twisted.tmall.common;

import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.service.IPermissionService;
import net.mshome.twisted.tmall.service.IRoleService;
import net.mshome.twisted.tmall.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

/**
 * 用户验证
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/3
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.getByUsername(username).orElseThrow(() -> new UnauthenticatedException("用户不存在"));
        if (user.getDataState() == DataState.INVALID) {
            throw new UnauthenticatedException("当前用户已失效，请联系管理员！");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = roleService.listCodesByUserId(user.getId());
        authorizationInfo.setRoles(roles);
        Set<String> permissions = permissionService.listCodesByRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (Objects.isNull(token.getPrincipal())) {
            return null;
        }
        String username = (String) token.getPrincipal();
        User user = userService.getByUsername(username).orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo();
        authorizationInfo.setPrincipals(new SimplePrincipalCollection(username, getName()));
        authorizationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        authorizationInfo.setCredentials(user.getPassword());
        return authorizationInfo;
    }

}
