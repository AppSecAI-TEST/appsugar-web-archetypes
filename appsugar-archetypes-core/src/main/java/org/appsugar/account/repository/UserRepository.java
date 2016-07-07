package org.appsugar.account.repository;

import org.appsugar.account.condition.UserCondition;
import org.appsugar.account.entity.User;
import org.appsugar.data.jpa.repository.IdEntityRepository;
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
	@Query(value = "delete  from as_user_role where role_id = ?1", nativeQuery = true)
	public void deleteRoleRelationship(Long roleId);

}
