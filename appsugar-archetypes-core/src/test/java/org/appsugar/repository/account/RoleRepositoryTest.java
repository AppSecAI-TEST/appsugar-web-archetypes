package org.appsugar.repository.account;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.RoleCondition;
import org.appsugar.repository.account.jpa.RoleJpaRepository;
import org.appsugar.repository.account.jpa.RoleSpecification;
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
		repository.saveAndFlush(role);
		Assert.assertNotNull(role.getId());
		logger.debug("test save role id is {}", role.getId());
	}

	@Test
	public void testFindBySpecification() {
		RoleCondition condition = new RoleCondition();
		List<Role> roleList = repository.findAll(new RoleSpecification(condition));
		logger.debug("testFindBySpecification {}", roleList);
		Assert.assertTrue(CollectionUtils.isNotEmpty(roleList));
	}
}
