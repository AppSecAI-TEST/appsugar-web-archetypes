package org.appsugar.security.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.appsugar.BaseSecurityTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author NewYoung
 * 2016年3月2日上午11:30:12
 */
public class ShiroReamlTest extends BaseSecurityTestCase {

	@Test
	public void testLogin() {
		String loginName = "admin";
		String password = "admin";
		Subject subject = ShiroUtils.getSubject();
		subject.login(new UsernamePasswordToken(loginName, password.toCharArray()));
		logger.debug("has permission {}", subject.isPermitted("user:view"));
		Assert.assertTrue(subject.isAuthenticated());
	}
}
