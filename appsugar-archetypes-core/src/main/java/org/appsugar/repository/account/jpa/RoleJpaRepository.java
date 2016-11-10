package org.appsugar.repository.account.jpa;

import org.appsugar.data.jpa.repository.JpaIdEntityRepository;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.RoleCondition;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:39:45
 */
public interface RoleJpaRepository extends JpaIdEntityRepository<Role, RoleCondition> {
}
