package org.appsugar.service.account.impl;

import java.util.List;
import java.util.Objects;

import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.AccountCondition;
import org.appsugar.repository.account.jpa.AccountRepository;
import org.appsugar.service.account.AccountService;
import org.appsugar.service.account.UserService;
import org.appsugar.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户服务管理实现
 * @author NewYoung
 * 2016年12月27日上午11:23:36
 */
@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, AccountCondition> implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserService userService;

	@Override
	public Account getByTypeAndKey(AccountType type, String key) {
		return accountRepository.findByTypeAndKey(type, key);
	}

	/**
	 * 查询数据库是否存在该账号
	 * 如果不存在创建账号,并创建user
	 * 没有处理多线程问题
	 */
	@Override
	public Account saveAccountIfNecessary(Account account) {
		Account oldAccount = accountRepository.findByTypeAndKey(account.getType(), account.getKey());
		if (Objects.isNull(oldAccount)) {
			User user = new User();
			user.setEmail(account.getProfile().getEmail());
			user.setName(account.getProfile().getName());
			userService.save(user);
			account.setUser(user);
			oldAccount = account;
		} else {
			oldAccount.setProfile(account.getProfile());
		}
		return accountRepository.save(oldAccount);
	}

	@Override
	public List<Account> getByUserId(Long id) {
		return accountRepository.findByUserId(id);
	}

}
