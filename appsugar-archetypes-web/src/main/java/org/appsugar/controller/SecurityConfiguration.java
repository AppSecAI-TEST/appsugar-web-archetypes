package org.appsugar.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.appsugar.controller.filter.FormAuthenticationFilterExtension;
import org.appsugar.security.ShiroRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

/**
 * shiro安全配置
 * @author NewYoung
 * 2016年6月25日上午11:02:58
 */
@Configuration
public class SecurityConfiguration {

	public static final String EXT_AUTHC_FILTER_NAME = "extAuthcFilter";

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap());
		Map<String, Filter> filters = Maps.newHashMap();
		filters.put(EXT_AUTHC_FILTER_NAME, new FormAuthenticationFilterExtension());
		filter.setFilters(filters);
		return filter;
	}

	/**
	 * 路径映射权限配置 
	 */
	public Map<String, String> filterChainDefinitionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("/", "anon");
		map.put("/static/**", "anon");
		map.put("/login", "anon");
		map.put("/logout", "logout");
		map.put("/**", EXT_AUTHC_FILTER_NAME);
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
