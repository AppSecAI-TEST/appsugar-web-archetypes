package org.appsugar.service.account;

import java.util.List;

import org.appsugar.condition.account.UserCondition;
import org.appsugar.entity.account.User;
import org.appsugar.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 用户管理
 * @author NewYoung
 * 2016年2月26日上午10:43:27
 */
public interface UserService extends GenericService<User> {

	/**
	 * 根据登录名查找用户
	 */
	public User getByLoginName(String loginName);

	/**
	 * 根据条件查询
	 */
	public Page<User> getByCondition(UserCondition condition, Pageable pageable);

	/**
	 * 异步获取
	 */
	public ListenableFuture<List<User>> getAllAsync();

}
