package org.appsugar.service.account;

import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.condition.AccountCondition;
import org.appsugar.service.GenericService;

/**
 * 账户服务管理
 * @author NewYoung
 * 2016年12月27日上午11:22:47
 */
public interface AccountService extends GenericService<Account, AccountCondition> {

	/**
	 * 根据账户类型与key查询账户信息
	 * @author NewYoung
	 * 2016年12月27日下午12:51:51
	 */
	public Account getByTypeAndKey(AccountType type, String key);

	/**
	 * 确保账号正常
	 * @author NewYoung
	 * 2016年12月27日下午1:07:46
	 */
	public Account saveAccountIfNecessary(Account account);
}
