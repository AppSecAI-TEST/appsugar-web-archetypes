package org.appsugar.repository.account;

import java.util.List;

import org.appsugar.dto.account.RoleUserDto;

/**
 * 角色dao扩展
 * @author NewYoung
 * 2016年2月23日下午6:55:01
 */
public interface RoleRepositoryCustom {
	/**
	 * 删除角色
	 */
	public void deleteRole(Long id);

	/**
	 * 统计角色用户数
	 */
	public List<RoleUserDto> getRoleStatistic();

}
