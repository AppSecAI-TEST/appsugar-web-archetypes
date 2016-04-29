package org.appsugar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * base dao test class
 * @author NewYoung
 * 2016年1月28日下午6:01:06
 */
@ContextConfiguration({ "classpath*:/applicationContext.xml", "classpath:/applicationContext-resource.xml" })
public abstract class BaseJpaDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(BaseJpaDaoTestCase.class);

}
