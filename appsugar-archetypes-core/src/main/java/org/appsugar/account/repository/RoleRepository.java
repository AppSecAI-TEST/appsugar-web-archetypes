package org.appsugar.repository.account;

import org.appsugar.condition.account.RoleCondition;
import org.appsugar.entity.account.Role;
import org.appsugar.repository.IdEntityRepository;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:39:45
 */
public interface RoleRepository extends IdEntityRepository<Role, RoleCondition> {

}
