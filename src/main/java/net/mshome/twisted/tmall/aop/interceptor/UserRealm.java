package net.mshome.twisted.tmall.aop.interceptor;

import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.service.IPermissionService;
import net.mshome.twisted.tmall.service.IRoleService;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.util.EntityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.getByUsername(username).orElseThrow(() -> new UnauthenticatedException("用户不存在"));
        if (user.getDataState() == DataState.INVALID) {
            throw new UnauthenticatedException("当前用户已失效，请联系管理员！");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roles = roleService.listByUserId(user.getId());
        Set<String> roleCodes = EntityUtils.collectToSet(roles, Role::getCode);
        List<Long> roleIds = EntityUtils.collectIds(roles);
        authorizationInfo.setRoles(roleCodes);
        Set<String> permissions = permissionService.listCodeByRoleIds(roleIds);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Assert.notNull(username, "请输入用户名");
        User user = userService.getByUsername(username).orElseThrow(() -> new UnknownAccountException("用户不存在"));
        SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo();
        authorizationInfo.setPrincipals(new SimplePrincipalCollection(username, getName()));
        authorizationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        authorizationInfo.setCredentials(user.getPassword());
        return authorizationInfo;
    }

    @Override
    public String getName() {
        return "Tmall-User-Realm";
    }

}
