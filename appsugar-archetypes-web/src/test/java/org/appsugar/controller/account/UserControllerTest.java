package org.appsugar.controller.account;

import org.appsugar.BaseControllerTestCase;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 
 * @author NewYoung
 * 2016年2月29日上午11:39:25
 */
public class UserControllerTest extends BaseControllerTestCase {

	@Test
	public void testList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/account/user").param("pageNum", "0").param("pageSize", "5"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
