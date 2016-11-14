package org.appsugar.repository.account.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.appsugar.extend.SpecificationQueryWrapper;
import org.appsugar.specification.IdEntitySpecification;
import org.springframework.stereotype.Component;

/**
 * 用户查询
 * name start like
 * loginName eq
 * @author NewYoung
 * 2016年2月23日下午2:29:57
 */
@Component
public class UserSpecification extends IdEntitySpecification<User, UserCondition> {

	public UserSpecification(UserCondition conditionObject) {
		super(conditionObject);
	}

	public UserSpecification() {
		this(new UserCondition());
	}

	@Override
	protected void addCondition(SpecificationQueryWrapper<User> query, Root<User> root, CriteriaBuilder cb,
			UserCondition condition) {
		super.addCondition(query, root, cb, condition);
		String name = condition.getName();
		if (StringUtils.isNotBlank(name)) {
			query.add(cb.like(root.get(User._name), name + "%"));
		}
		String loginName = condition.getLoginName();
		if (StringUtils.isNotBlank(loginName)) {
			query.add(cb.equal(root.get(User._loginName), loginName));
		}
	}

}
