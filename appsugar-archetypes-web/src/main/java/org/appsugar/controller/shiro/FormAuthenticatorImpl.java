package org.appsugar.controller.shiro;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountProfile;
import org.appsugar.entity.account.AccountType;
import org.appsugar.security.form.FormAuthenticator;
import org.appsugar.service.account.AccountService;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class FormAuthenticatorImpl extends FormAuthenticator {

	public static final String rememberMeKeyName = "rememberMe";

	@Autowired
	private AccountService accountService;

	@Override
	public void validate(UsernamePasswordCredentials credentials, WebContext context) throws HttpAction {
		String loginName = credentials.getUsername();
		Account account = accountService.getByTypeAndKey(AccountType.FORM, loginName);
		if (Objects.isNull(account) || !Objects.equals(account.getSecret(), credentials.getPassword())) {
			throw new CredentialsException("username or password error");
		}
		CommonProfile profile = new CommonProfile();
		profile.setId(loginName);
		profile.setClientName(credentials.getClientName());
		profile.setRemembered(StringUtils.isNoneBlank(context.getRequestParameter(rememberMeKeyName)));
		Map<String, Object> attribute = Maps.newHashMap();
		AccountProfile p = account.getProfile();
		attribute.put("display_name", p.getName());
		attribute.put("email", p.getEmail());
		attribute.put("picture_url", p.getPictureUrl());
		attribute.put(Pac4jConstants.USERNAME, loginName);
		profile.addAttributes(attribute);
		credentials.setUserProfile(profile);
	}
}
