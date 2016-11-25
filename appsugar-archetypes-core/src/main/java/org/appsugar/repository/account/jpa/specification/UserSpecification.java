package org.appsugar.repository.account.jpa.specification;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.data.common.repository.querydsl.PredicateCollection;
import org.appsugar.data.jpa.repository.querdsl.JpaQueryDslSpecification;
import org.appsugar.entity.account.QUser;
import org.appsugar.entity.account.condition.UserCondition;
import org.springframework.stereotype.Component;

/**
 * 用户查询
 * name start like
 * loginName eq
 * @author NewYoung
 * 2016年2月23日下午2:29:57
 */
@Component
public class UserSpecification extends JpaQueryDslSpecification<UserCondition, QUser> {

	@Override
	public void toPredicate(PredicateCollection pc, UserCondition condition, QUser root) {
		super.toPredicate(pc, condition, root);
		String name = condition.getName();
		if (StringUtils.isNotBlank(name)) {
			pc.add(root.name.startsWith(name));
		}
		String loginName = condition.getLoginName();
		if (StringUtils.isNotBlank(loginName)) {
			pc.add(root.loginName.eq(loginName));
		}
	}

}
