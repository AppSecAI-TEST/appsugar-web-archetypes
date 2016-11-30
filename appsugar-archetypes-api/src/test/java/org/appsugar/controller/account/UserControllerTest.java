package org.appsugar.controller.account;

import org.appsugar.BaseControllerTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 用户控制器测试
 * @author NewYoung
 * 2016年11月30日下午4:21:53
 */
public class UserControllerTest extends BaseControllerTestCase {

	@Test
	public void testList() throws Exception {
		String name = "管理";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/account/user/list").param("name", name)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		logger.debug("testList result is {}", content);
		Assert.assertNotNull(content);
		Assert.assertTrue(content.contains(name));
	}
}
