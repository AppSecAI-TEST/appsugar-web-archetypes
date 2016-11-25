package org.appsugar.repository.account.jpa;

import java.util.List;

import org.appsugar.entity.account.dto.RoleStatDto;

/**
 * 角色统计访问接口
 * @author NewYoung
 * 2016年11月25日下午1:48:08
 */
public interface RoleStatRepository {
	/**
	 * 统计角色用户量
	 * @author NewYoung
	 * 2016年11月25日下午1:54:56
	 */
	public List<RoleStatDto> getRoleStat();
}
