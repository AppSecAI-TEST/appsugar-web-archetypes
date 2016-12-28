package org.appsugar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.appsugar.security.form.Pac4jCasLoginConfiguration;
import org.appsugar.security.form.Pac4jCasOauth2LoginConfiguration;
import org.appsugar.security.form.Pac4jFormLoginConfiguration;
import org.appsugar.security.parent.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * shiro安全配置
 * @author NewYoung
 * 2016年6月25日上午11:02:58
 */
@Configuration
@Import({ Pac4jFormLoginConfiguration.class, Pac4jCasLoginConfiguration.class, Pac4jCasOauth2LoginConfiguration.class })
public class SecurityConfiguration {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager,
			@Qualifier(Constants.FILTER_MAPPING_NAME) Map<String, Filter> filters) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);
		filter.setLoginUrl("/login");
		filter.setSuccessUrl("/main");
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap());
		filter.setFilters(filters);
		return filter;
	}

	public Map<String, String> filterChainDefinitionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("/favicon.ico", "anon");
		map.put("/static/**", "anon");
		map.put("/assets/**", "anon");
		map.put("/webjars/**", "anon");
		map.put("/logout", "logout");
		map.put("/login_callback", Constants.CALLBACK_FILTER_NAME);
		map.put("/login/cas", Constants.CAS_FILTER_NAME);
		map.put("/login/oauth2", Constants.CAS_OAUTH2_FILTER_NAME);
		map.put("/**", "user");
		return map;
	}

}
