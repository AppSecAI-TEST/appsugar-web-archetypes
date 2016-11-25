package org.appsugar.repository.account.jpa;

import java.util.List;

import org.appsugar.entity.account.QRole;
import org.appsugar.entity.account.QUser;
import org.appsugar.entity.account.dto.RoleStatDto;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.querydsl.core.support.QueryMixin.Role;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

/**
 * 角色统计数据访问接口实现
 * @author NewYoung
 * 2016年11月25日下午1:53:23
 */
@Component
public class RoleStatRepositoryImpl extends QueryDslRepositorySupport implements RoleStatRepository {

	public RoleStatRepositoryImpl() {
		super(Role.class);
	}

	@Override
	public List<RoleStatDto> getRoleStat() {
		QUser user = QUser.user;
		QRole userRole = QRole.role;
		JPQLQuery<RoleStatDto> query = getQuerydsl().createQuery();
		return query.from(user).rightJoin(user.roleList, userRole).groupBy(userRole.id)
				.select(Projections.constructor(RoleStatDto.class, userRole.id, userRole.name, user.id.count()))
				.fetch();
	}

}
