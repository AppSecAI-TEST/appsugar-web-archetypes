package org.appsugar.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
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

}
