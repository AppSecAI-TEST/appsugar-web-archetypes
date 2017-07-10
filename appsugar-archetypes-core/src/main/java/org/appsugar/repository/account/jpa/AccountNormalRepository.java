package org.appsugar.repository.account.jpa;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.data.jpa.repository.JpaIdEntityRepository;
import org.appsugar.entity.account.Account;

public interface AccountNormalRepository extends JpaIdEntityRepository<Account, LongIdEntityCondition> {

}
