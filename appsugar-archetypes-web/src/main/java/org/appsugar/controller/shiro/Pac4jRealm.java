package org.appsugar.controller.shiro;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;

/**
 * pac4j 统一认证领域
 * @author NewYoung
 * 2016年12月23日下午5:22:18
 */
public class Pac4jRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(Pac4jRealm.class);

	public Pac4jRealm() {
		setAuthenticationTokenClass(Pac4jToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authenticationToken)
			throws AuthenticationException {
		final Pac4jToken token = (Pac4jToken) authenticationToken;
		final LinkedHashMap<String, CommonProfile> profiles = token.getProfiles();
		logger.debug("pac4j toekn  profiles is {}", profiles);
		final Pac4jPrincipal principal = new Pac4jPrincipal(profiles);
		final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
		return new SimpleAuthenticationInfo(principalCollection, profiles.hashCode());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		final Set<String> roles = new HashSet<>();
		final Set<String> permissions = new HashSet<>();
		final Pac4jPrincipal principal = principals.oneByType(Pac4jPrincipal.class);
		if (principal != null) {
			final List<CommonProfile> profiles = principal.getProfiles();
			for (CommonProfile profile : profiles) {
				if (profile != null) {
					roles.addAll(profile.getRoles());
					permissions.addAll(profile.getPermissions());
				}
			}
		}

		final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roles);
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

}
