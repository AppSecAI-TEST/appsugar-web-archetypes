package org.appsugar.security;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.User;
import org.appsugar.service.account.AccountService;
import org.appsugar.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * 領域認證
 * @author NewYoung
 * 2016年3月2日上午10:40:57
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String loginName = userToken.getUsername();
		String password = new String(userToken.getPassword());
		Account account = accountService.getByTypeAndKey(AccountType.FORM, loginName);
		if (account == null || !Objects.equal(account.getSecret(), password)) {
			return null;
		}
		User user = userService.get(account.getUser().getId());
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
		List<String> permissionList = Lists.newArrayList();
		List<String> roleList = Lists.newArrayList();
		permissionList.addAll(user.getPermissionList());
		for (Role role : user.getRoleList()) {
			roleList.add(role.getName());
			permissionList.addAll(role.getPermissionList());
		}
		info.addRoles(roleList);
		info.addStringPermissions(permissionList);
		principal.setAttribute(Principal.PERMISSION_ATTRIBUTE_KEY,
				permissionList.stream().distinct().collect(Collectors.toList()));
		principal.setAttribute(Principal.ROLE_ATTRIBUTE_KEY, roleList);
		return info;
	}

	protected Principal getUserPrincipal(User user) {
		Principal prin = new Principal(user.getId(), user.getName());
		prin.setAttribute("name", user.getName());
		return prin;
	}

}
