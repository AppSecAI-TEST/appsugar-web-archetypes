package org.appsugar.repository.account;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.appsugar.repository.account.jpa.UserJpaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * user repository test case
 * @author NewYoung
 * 2016年1月28日下午6:04:19
 */
public class UserRepositoryTest extends BaseJpaDaoTestCase {

	@Autowired
	private UserJpaRepository repository;

	@Test
	public void testFindByLoginName() {
		String loginName = "admin";
		User user = repository.findByLoginName(loginName);
		logger.debug("testFindByLoginName login name is {}  result is : {}", loginName, user);
		Assert.assertNotNull("User not exist by login name " + loginName, user);
	}

	@Test
	public void testFindByCondition() {
		UserCondition condition = new UserCondition();
		String name = "管理员";
		condition.setName(name);
		List<User> userList = repository.findByCondition(condition);
		logger.debug("testFindByCondition  name {}  result {}", name, userList);
		Assert.assertTrue(CollectionUtils.isNotEmpty(userList));
	}

	@Test
	public void testDeleteRoleRelationship() {
		Long roleId = -9999l;
		repository.deleteRoleRelationship(roleId);
	}
}
