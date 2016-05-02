package org.appsugar.repository.account;

import org.appsugar.condition.account.UserCondition;
import org.appsugar.entity.account.User;
import org.appsugar.repository.IdEntityRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author NewYoung
 * 2016年2月22日下午1:00:51
 */
public interface UserRepository extends IdEntityRepository<User, UserCondition> {
	/**
	 * 根据名称查询用户
	 */
	public User findByLoginName(String name);

	/**
	 * 删除所有用户与这个角色的关系
	 */
	@Modifying
	@Query(value = "delete as_user_role where role_id = ?1", nativeQuery = true)
	public void deleteRoleRelationship(Long roleId);

}
