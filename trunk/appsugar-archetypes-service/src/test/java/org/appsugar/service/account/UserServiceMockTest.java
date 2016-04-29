package org.appsugar.service.account;

import static org.mockito.Mockito.when;

import org.appsugar.BaseServiceMockTestCase;
import org.appsugar.entity.account.User;
import org.appsugar.repository.account.UserRepository;
import org.appsugar.service.account.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

/**
 * 
 * @author NewYoung
 * 2016年2月26日下午2:25:46
 */
public class UserServiceMockTest extends BaseServiceMockTestCase {

	private UserService userService;

	@Mock
	private UserRepository userRepository;

	public UserServiceMockTest() {
		userService = new UserServiceImpl().setUserRepository(userRepository);
	}

	@Test
	public void testGetByName() {
		String loginName = "admin";
		User user = new User();
		when(userRepository.findByLoginName(loginName)).thenReturn(user);
		Assert.assertEquals(user, userService.getByLoginName(loginName));
	}
}
