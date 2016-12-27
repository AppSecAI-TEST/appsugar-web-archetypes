package org.appsugar.repository.account.jpa;

import org.appsugar.data.jpa.repository.JpaIdEntityRepository;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author NewYoung
 * 2016年2月22日下午1:00:51
 */
public interface UserJpaRepository extends JpaIdEntityRepository<User, UserCondition> {

	/**
	 * 删除所有用户与这个角色的关系
	 */
	@Modifying
	@Query(value = "delete  from as_user_role where role_id = ?1", nativeQuery = true)
	public void deleteRoleRelationship(Long roleId);

}
