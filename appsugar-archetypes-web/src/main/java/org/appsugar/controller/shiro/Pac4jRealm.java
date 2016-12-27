package org.appsugar.controller.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountProfile;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.User;
import org.appsugar.service.account.AccountService;
import org.appsugar.service.account.UserService;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Objects;

import io.buji.pac4j.token.Pac4jToken;

/**
 * pac4j 统一认证领域
 * @author NewYoung
 * 2016年12月23日下午5:22:18
 */
@Component
public class Pac4jRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(Pac4jRealm.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	public Pac4jRealm() {
		setAuthenticationTokenClass(Pac4jToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authenticationToken)
			throws AuthenticationException {
		final Pac4jToken token = (Pac4jToken) authenticationToken;
		final LinkedHashMap<String, CommonProfile> profiles = token.getProfiles();
		List<Account> accountList = profiles.values().stream().map(this::makeSureProfile).collect(Collectors.toList());
		final Principal principal = new Principal(accountList);
		final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
		return new SimpleAuthenticationInfo(principalCollection, profiles.hashCode());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		Principal principal = principals.oneByType(Principal.class);
		List<Account> accountList = principal.getAccountList();
		Optional<User> matchedUser = accountList.stream().map(Account::getUser).findFirst();
		if (!matchedUser.isPresent()) {
			logger.warn("User not found in account {}", accountList);
			return simpleAuthorizationInfo;
		}
		User user = userService.get(matchedUser.get().getId());
		simpleAuthorizationInfo.addStringPermissions(user.getPermissionList());
		user.getRoleList().forEach(e -> {
			simpleAuthorizationInfo.addRole(e.getName());
			simpleAuthorizationInfo.addStringPermissions(e.getPermissionList());
		});
		return simpleAuthorizationInfo;
	}

	/**
	 * 确保第三方账号在系统中存在
	 * @author NewYoung
	 * 2016年12月27日下午1:23:40
	 */
	protected Account makeSureProfile(CommonProfile profile) {
		String clientName = profile.getClientName().toUpperCase();
		if (clientName.endsWith("CLIENT")) {
			clientName = clientName.substring(0, clientName.length() - 6);
		}
		AccountType accountType = AccountType.valueOf(clientName);
		if (Objects.equal(accountType, AccountType.FORM)) {
			return accountService.getByTypeAndKey(AccountType.FORM, profile.getId());
		}
		Account account = new Account();
		account.setType(accountType);
		account.setKey(profile.getId());
		AccountProfile p = new AccountProfile();
		p.setEmail(profile.getEmail());
		p.setName(profile.getDisplayName());
		p.setPictureUrl(profile.getPictureUrl());
		return accountService.saveAccountIfNecessary(account);
	}
}
