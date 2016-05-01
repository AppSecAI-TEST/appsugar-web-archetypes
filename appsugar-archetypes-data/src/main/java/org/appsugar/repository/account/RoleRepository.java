package org.appsugar.repository.account;

import java.util.List;

import org.appsugar.dto.account.RoleUserDto;
import org.appsugar.entity.account.Role;
import org.appsugar.repository.IdEntityRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:39:45
 */
public interface RoleRepository extends IdEntityRepository<Role>, RoleRepositoryCustom {

	/**
	 * 统计角色用户数
	 */
	@Query("select new org.appsugar.dto.account.RoleUserDto(r.id,r.name,r.title,count(u)) from Role r left join r.userList u where r.id = ?1 group by r")
	public List<RoleUserDto> findRoleStatistic(Long roleId);
}
