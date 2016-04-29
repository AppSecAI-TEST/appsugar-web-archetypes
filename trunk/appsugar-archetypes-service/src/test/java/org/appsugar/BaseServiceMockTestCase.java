package org.appsugar;

import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/**
 * base service mock test class
 * @author NewYoung
 * 2016年1月28日下午6:01:06
 * {@link http://mockito.github.io/mockito/docs/current/org/mockito/Mockito.html}
 */
public abstract class BaseServiceMockTestCase extends TestCase {
	protected final Logger logger = LoggerFactory.getLogger(BaseServiceMockTestCase.class);

	public BaseServiceMockTestCase() {
		MockitoAnnotations.initMocks(this);
	}
}
