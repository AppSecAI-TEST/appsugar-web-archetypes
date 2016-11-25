package org.appsugar.repository.account.jpa.specification;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.data.common.repository.querydsl.PredicateCollection;
import org.appsugar.data.jpa.repository.querdsl.JpaQueryDslSpecification;
import org.appsugar.entity.account.QRole;
import org.appsugar.entity.account.condition.RoleCondition;
import org.springframework.stereotype.Component;

/**
 * 
 * @author NewYoung
 * 2016年11月24日下午1:10:00
 */
@Component
public class RoleSpecification extends JpaQueryDslSpecification<RoleCondition, QRole> {

	@Override
	public void toPredicate(PredicateCollection pc, RoleCondition condition, QRole root) {
		super.toPredicate(pc, condition, root);
		String name = condition.getName();
		if (StringUtils.isNotBlank(name)) {
			pc.add(root.name.eq(name));
		}
		String title = condition.getTitle();
		if (StringUtils.isNotBlank(title)) {
			pc.add(root.title.startsWith(title));
		}
	}

}
