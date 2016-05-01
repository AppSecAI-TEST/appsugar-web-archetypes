package org.appsugar.security.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.User;
import org.appsugar.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 領域認證
 * @author NewYoung
 * 2016年3月2日上午10:40:57
 */
public class ShiroRealm extends AuthorizingRealm {

	private UserService userService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String loginName = userToken.getUsername();
		String password = new String(userToken.getPassword());
		User user = userService.getByLoginName(loginName);
		if (user == null) {
			return null;
		}
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return new SimpleAuthenticationInfo(getUserPrincipal(user), password, getName());
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (!principals.fromRealm(getName()).iterator().hasNext()) {
			return null;
		}
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = userService.get(principal.id);
		for (String permission : user.getPermissionList()) {
			info.addStringPermission(permission);
		}
		for (Role role : user.getRoleList()) {
			info.addRole(role.getName());
			for (String permission : role.getPermissionList()) {
				info.addStringPermission(permission);
			}
		}
		return info;
	}

	protected Principal getUserPrincipal(User user) {
		Principal prin = new Principal(user.getId(), user.getName());
		prin.setAttribute("name", user.getName());
		return prin;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
