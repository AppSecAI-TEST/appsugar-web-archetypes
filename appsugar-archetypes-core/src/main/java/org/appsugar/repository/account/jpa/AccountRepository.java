package org.appsugar.repository.account.jpa;

import java.util.List;

import org.appsugar.data.jpa.repository.JpaIdEntityRepository;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.condition.AccountCondition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

	/**
	 * 密钥添加后缀
	 * @author NewYoung
	 * 2017年6月22日下午5:05:12
	 */
	@Modifying
	@Query("update Account a set a.secret=concat(a.secret,?2) where id = ?1")
	public void updateSecretConcatWith(Long id, String suffix);

	/**
	 * 根据用户id查询
	 */
	public List<Account> findByUserId(Long id);
}
