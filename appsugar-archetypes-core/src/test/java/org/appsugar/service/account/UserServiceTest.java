package org.appsugar.service.account;

import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.account.entity.User;
import org.appsugar.account.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseJpaDaoTestCase {

	@Autowired
	private UserService userService;

	@Test
	public void testGetByLoginName() {
		String loginName = "admin";
		User user = userService.getByLoginName(loginName);
		logger.debug("testGetByLoginName loginName {} result {}", loginName, user);
	}

}
