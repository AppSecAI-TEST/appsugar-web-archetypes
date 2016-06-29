package org.appsugar;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.appsugar.security.ShiroRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试环境配置
 * @author NewYoung
 * 2016年6月24日下午11:46:44
 */
@Configuration
public class TestConfiguration {

	@Bean
	public SecurityManager securityManager(ShiroRealm shiroRealm, EhCacheManager ecm) {
		DefaultSecurityManager s = new DefaultSecurityManager();
		s.setRealm(shiroRealm);
		s.setCacheManager(ecm);
		return s;
	}
}
