package org.appsugar.repository.account.jpa;

import org.appsugar.data.jpa.repository.JpaIdEntityRepository;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.condition.AccountCondition;

/**
 * 用户账户数据访问接口
 * @author NewYoung
 * 2016年12月27日上午11:07:39
 */
public interface AccountRepository extends JpaIdEntityRepository<Account, AccountCondition> {
	/**
	 * 根据账户类型与key查询账户信息
	 * @author NewYoung
	 * 2016年12月27日下午12:52:42
	 */
	public Account findByTypeAndKey(AccountType type, String key);
}
