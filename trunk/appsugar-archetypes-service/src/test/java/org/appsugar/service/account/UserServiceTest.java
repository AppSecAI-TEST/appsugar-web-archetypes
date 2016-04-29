package org.appsugar.service.account;

import org.appsugar.BaseJpaServiceTestCase;
import org.appsugar.entity.account.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseJpaServiceTestCase {

	@Autowired
	private UserService userService;

	@Test
	public void testGetByLoginName() {
		String loginName = "admin";
		User user = userService.getByLoginName(loginName);
		logger.debug("testGetByLoginName loginName {} result {}", loginName, user);
	}
}
