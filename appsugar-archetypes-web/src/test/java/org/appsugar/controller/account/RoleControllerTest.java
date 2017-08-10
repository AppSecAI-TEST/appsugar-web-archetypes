package org.appsugar.controller.account;

import org.appsugar.BaseControllerTestCase;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 角色控制器test
 * @author NewYoung
 * 2016年3月3日上午11:30:01
 */
public class RoleControllerTest extends BaseControllerTestCase {

	@Test
	public void testList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/account/role").param("pageNum", "0").param("pageSize", "5"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/account/role/form?id=-1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
