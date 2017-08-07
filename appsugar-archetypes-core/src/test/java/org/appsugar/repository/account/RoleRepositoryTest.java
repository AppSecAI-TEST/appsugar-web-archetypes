package org.appsugar.repository.account;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.condition.RoleCondition;
import org.appsugar.repository.account.jpa.RoleJpaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:50:59
 */
public class RoleRepositoryTest extends BaseJpaDaoTestCase {

	@Autowired
	private RoleJpaRepository repository;

	@Test
	public void testSave() {
		Role role = new Role("testSave", "title");
		role = repository.saveAndFlush(role);
		Assert.assertNotNull(role.getId());
		logger.debug("test save role  is {}", role);
		role.setName("dynamic update");
		repository.saveAndFlush(role);
	}

	@Test
	public void testFindBySpecification() {
		RoleCondition condition = new RoleCondition();
		condition.setName("SUPER_ADMIN");
		List<Role> roleList = repository.findByCondition(condition);
		logger.debug("testFindBySpecification {}", roleList);
		Assert.assertTrue(CollectionUtils.isNotEmpty(roleList));
	}

}
