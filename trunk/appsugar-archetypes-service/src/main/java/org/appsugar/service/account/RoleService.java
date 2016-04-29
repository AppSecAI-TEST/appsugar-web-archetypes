package org.appsugar.service.account;

import org.appsugar.condition.account.RoleCondition;
import org.appsugar.entity.account.Role;
import org.appsugar.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 角色管理
 * @author NewYoung
 * 2016年3月3日上午10:58:43
 */
public interface RoleService extends GenericService<Role> {

	public Page<Role> getByCondition(RoleCondition condition, Pageable pageable);
}
