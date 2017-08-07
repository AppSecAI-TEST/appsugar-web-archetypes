package org.appsugar.repository.common;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.entity.QLongIdEntity;
import org.appsugar.data.jpa.repository.querdsl.JpaQueryDslSpecification;
import org.springframework.stereotype.Component;

/**
 * 默认查询条件实现
 * @author NewYoung
 * 2017年7月10日上午10:32:52
 */
@Component
public class LongIdEntitySpecification extends JpaQueryDslSpecification<LongIdEntityCondition, QLongIdEntity> {

}
