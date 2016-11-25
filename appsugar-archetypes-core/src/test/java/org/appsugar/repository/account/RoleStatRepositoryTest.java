package org.appsugar.repository.account;

import java.util.List;

import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.account.dto.RoleStatDto;
import org.appsugar.repository.account.jpa.RoleStatRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色统计数据接口测试
 * @author NewYoung
 * 2016年11月25日下午2:05:01
 */
public class RoleStatRepositoryTest extends BaseJpaDaoTestCase {
	@Autowired
	private RoleStatRepository repository;

	@Test
	public void testGetRoleStat() {
		List<RoleStatDto> result = repository.getRoleStat();
		logger.debug("testGetRoleStat  result is {}", result);
	}
}
