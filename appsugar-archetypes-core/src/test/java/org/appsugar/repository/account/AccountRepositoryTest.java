package org.appsugar.repository.account;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.condition.AccountCondition;
import org.appsugar.repository.account.jpa.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 账户数据接口测试
 * @author NewYoung
 * 2016年12月27日上午11:25:19
 */
public class AccountRepositoryTest extends BaseJpaDaoTestCase {

	@Autowired
	private AccountRepository repository;

	@Test
	public void testFindBySpecification() {
		AccountCondition condition = new AccountCondition();
		condition.setAccountType(AccountType.FORM);
		condition.setKey("admin");
		condition.setSecret("admin");
		List<Account> result = repository.findByCondition(condition);
		logger.debug("testFindBySpecification condition {} result is{}", condition, result);
		Assert.assertTrue(CollectionUtils.isNotEmpty(result));
	}

	@Test
	public void testFindByTypeAndKey() {
		AccountType type = AccountType.FORM;
		String key = "admin";
		Account account = repository.findByTypeAndKey(type, key);
		logger.debug("testFindByTypeAndKey  type is {} key is {} result is {}", type, key, account);
		Assert.assertNotNull(account);
	}

	@Test
	public void testUpdateSecretConcatWith() {
		String suffix = "iloveu";
		Long id = -1L;
		repository.updateSecretConcatWith(id, suffix);
		Account account = repository.findOne(id);
		Assert.assertTrue(account.getSecret().endsWith(suffix));
	}
}
