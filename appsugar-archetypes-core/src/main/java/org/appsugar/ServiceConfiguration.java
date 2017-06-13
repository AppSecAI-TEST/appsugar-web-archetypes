package org.appsugar;

import java.util.Properties;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 配置事物与shiro
 * @author NewYoung
 * 2016年6月24日下午7:02:38
 */

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceConfiguration {

	@Bean
	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager tm) {
		Properties p = new Properties();
		p.put("get*", "readonly");
		p.put("*", "required");
		return new TransactionInterceptor(tm, p);
	}

	@Bean
	public Advisor transactionAdvisor(PlatformTransactionManager tm) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* *..service..*ServiceImpl.*(..))");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, transactionInterceptor(tm));
		advisor.setOrder(0);
		return advisor;
	}

}
