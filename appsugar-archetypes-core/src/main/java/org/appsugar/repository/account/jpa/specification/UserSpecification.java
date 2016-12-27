package org.appsugar.repository.account.jpa.specification;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.data.common.repository.querydsl.PredicateCollection;
import org.appsugar.data.jpa.repository.querdsl.JpaQueryDslSpecification;
import org.appsugar.entity.account.Gender;
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
		String phone = condition.getPhone();
		if (StringUtils.isNotBlank(phone)) {
			pc.add(root.phone.startsWith(phone));
		}
		String email = condition.getEmail();
		if (StringUtils.isNotBlank(email)) {
			pc.add(root.email.startsWith(email));
		}
		Gender gender = condition.getGender();
		if (Objects.nonNull(gender)) {
			pc.add(root.gender.eq(gender));
		}
	}

}
