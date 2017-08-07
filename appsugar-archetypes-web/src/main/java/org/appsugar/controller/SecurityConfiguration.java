package org.appsugar.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.appsugar.security.ShiroRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro安全配置
 * @author NewYoung
 * 2016年6月25日上午11:02:58
 */
@Configuration
public class SecurityConfiguration {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);
		filter.setLoginUrl("/login");
		filter.setSuccessUrl("/main");
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap());
		return filter;
	}

	public Map<String, String> filterChainDefinitionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("/favicon.ico", "anon");
		map.put("/static/**", "anon");
		map.put("/assets/**", "anon");
		map.put("/webjars/**", "anon");
		map.put("/logout", "logout");
		map.put("/login", "authc");
		map.put("/**", "user");
		return map;
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
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
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

	/**
	 * shiro 安全管理
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, CacheManager shiroCacheManager) {
		DefaultWebSecurityManager stm = new DefaultWebSecurityManager();
		stm.setRealms(Arrays.asList(shiroRealm));
		stm.setCacheManager(shiroCacheManager);
		String cipherKey = "kPH+bIxk5D2deZiIxcaaaA==";
		CookieRememberMeManager crmm = new CookieRememberMeManager();
		crmm.setCipherKey(Base64.getDecoder().decode(cipherKey));
		stm.setRememberMeManager(crmm);
		return stm;
	}

	@Bean
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}
}
