package org.appsugar;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.appsugar.common.security.ShiroRealm;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * 配置事物与shiro
 * @author NewYoung
 * 2016年6月24日下午7:02:38
 */

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource("classpath:/applicationContext-service.xml")
public class ServiceConfiguration {

	/**
	 * 认证领域
	 */
	@Bean
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}

	/**
	 * shiro缓存
	 */
	@Bean
	public EhCacheManager shiroCacheManager() {
		EhCacheManager cache = new EhCacheManager();
		cache.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
		return cache;
	}

	/**
	 * 保证shiro内部bean执行
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * shiro 授权
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor shiroAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
