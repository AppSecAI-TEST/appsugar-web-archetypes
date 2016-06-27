package org.appsugar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * base service test class
 * @author NewYoung
 * 2016年1月28日下午6:01:06
 */
@SpringBootTest(classes = { ServiceConfiguration.class, RepositoryConfiguration.class, TestConfiguration.class })
public abstract class BaseJpaServiceTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(BaseJpaServiceTestCase.class);

}
