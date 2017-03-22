package org.appsugar.repository.account.jpa.specification;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.data.common.repository.querydsl.PredicateCollection;
import org.appsugar.data.jpa.repository.querdsl.JpaQueryDslSpecification;
import org.appsugar.entity.account.AccountType;
import org.appsugar.entity.account.QAccount;
import org.appsugar.entity.account.condition.AccountCondition;
import org.springframework.stereotype.Component;

/**
 * 账户查询器
 * @author NewYoung
 * 2016年12月27日上午11:09:54
 */
@Component
public class AccountSpecification extends JpaQueryDslSpecification<AccountCondition, QAccount> {

	@Override
	public void toPredicate(PredicateCollection pc, AccountCondition condition, QAccount root) {
		super.toPredicate(pc, condition, root);
		String key = condition.getKey();
		if (StringUtils.isNotBlank(key)) {
			pc.add(root.key.eq(key));
		}
		String secret = condition.getSecret();
		if (StringUtils.isNotBlank(secret)) {
			pc.add(root.secret.eq(secret));
		}
		AccountType accountType = condition.getAccountType();
		if (Objects.nonNull(accountType)) {
			pc.add(root.type.eq(accountType));
		}
	}

}
