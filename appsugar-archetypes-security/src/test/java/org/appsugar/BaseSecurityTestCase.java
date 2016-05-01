package org.appsugar;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author NewYoung
 * 2016年1月28日下午6:01:06
 */
@ContextConfiguration({ "classpath*:/applicationContext.xml", "classpath:/applicationContext-resource.xml",
		"classpath:/applicationContext-shiro.xml" })
public abstract class BaseSecurityTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(BaseSecurityTestCase.class);

	@Autowired
	protected SecurityManager sm;

	@Before
	public void setUp() throws Exception {
		ThreadContext.bind(sm);
	}

	@After
	public void after() throws Exception {
		ThreadContext.unbindSecurityManager();
	}
}
