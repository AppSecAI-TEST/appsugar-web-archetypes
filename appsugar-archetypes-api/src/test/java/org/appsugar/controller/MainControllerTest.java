package org.appsugar.controller;

import org.appsugar.BaseControllerTestCase;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 测试登录登出
 * @author NewYoung
 * 2016年11月9日下午1:00:53
 */
public class MainControllerTest extends BaseControllerTestCase {

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login").param("username", "admin").param("password", "admin"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
