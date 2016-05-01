package org.appsugar.controller.account;

import org.appsugar.BaseControllerTestCase;
import org.appsugar.condition.account.UserCondition;
import org.appsugar.repository.extend.PageAdapter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 * 
 * @author NewYoung
 * 2016年2月29日上午11:39:25
 */
public class UserControllerTest extends BaseControllerTestCase {

	@Autowired
	private UserController controller;

	@Test
	public void testList() {
		RedirectAttributesModelMap model = new RedirectAttributesModelMap();
		controller.list(model, new UserCondition(), new PageAdapter());
	}

	@Test
	public void testListUser() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/account/user/listUser")
				.accept(MediaType.parseMediaType(MEDIA_TYPE_APPLICATION_JSON_UTF8))).andReturn();
		logger.debug("contetn is {}", result.getResponse().getContentAsString());
	}

}
