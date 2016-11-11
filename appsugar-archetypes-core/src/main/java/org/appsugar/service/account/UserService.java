package org.appsugar.service.account;

import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.appsugar.service.GenericService;

/**
 * 用户管理
 * @author NewYoung
 * 2016年2月26日上午10:43:27
 */
public interface UserService extends GenericService<User, UserCondition> {

	/**
	 * 根据登录名查找用户
	 */
	public User getByLoginName(String loginName);

}
