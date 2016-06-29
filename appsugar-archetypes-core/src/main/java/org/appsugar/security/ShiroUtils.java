package org.appsugar.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

	/**
	 * 获取当前主题信息
	 */
	public static final Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取用户登录信息
	 */
	public static final Principal getPrincipal() {
		return (Principal) getSubject().getPrincipal();
	}
}
