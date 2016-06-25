package org.appsugar;

import org.appsugar.controller.ControllerConfiguration;
import org.appsugar.entity.account.User;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * base service test class
 * @author NewYoung
 * 2016年1月28日下午6:01:06
 * 
 */
@SpringApplicationConfiguration({ ControllerConfiguration.class, SecurityConfiguration.class,
		ResourceConfiguration.class, ServiceConfiguration.class, RepositoryConfiguration.class })
@WebAppConfiguration
public abstract class BaseControllerTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	protected final String MEDIA_TYPE_APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

	protected final Logger logger = LoggerFactory.getLogger(BaseControllerTestCase.class);

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		ShiroTestUtils.mockSubject(new User());
	}

	@org.junit.After
	public void clearUp() {
		ShiroTestUtils.clearSubject();
	}

}
