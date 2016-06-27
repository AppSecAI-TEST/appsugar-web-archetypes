package org.appsugar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * base dao test class
 * @author NewYoung
 * 2016年1月28日下午6:01:06
 */
@SpringBootTest(classes = RepositoryConfiguration.class)
public abstract class BaseJpaDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(BaseJpaDaoTestCase.class);

}
