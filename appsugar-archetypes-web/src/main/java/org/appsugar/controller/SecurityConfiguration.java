package org.appsugar.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.appsugar.common.security.ShiroRealm;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.SecurityFilter;

/**
 * shiro安全配置
 * @author NewYoung
 * 2016年6月25日上午11:02:58
 */
@Configuration
public class SecurityConfiguration {
	/**
	 * shiro 安全管理
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, CacheManager shiroCacheManager) {
		DefaultWebSecurityManager stm = new DefaultWebSecurityManager();
		stm.setRealms(Arrays.asList(shiroRealm, new org.appsugar.controller.shiro.Pac4jRealm()));
		stm.setCacheManager(shiroCacheManager);
		return stm;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);
		filter.setLoginUrl("/login");
		filter.setSuccessUrl("/main");
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap());
		filter.setFilters(filters());
		return filter;
	}

	public Map<String, String> filterChainDefinitionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("/favicon.ico", "anon");
		map.put("/static/**", "anon");
		map.put("/assets/**", "anon");
		map.put("/webjars/**", "anon");
		map.put("/login", "authc");
		map.put("/logout", "logout");
		map.put("/cas_login", "casSecurityFilter");
		map.put("/login_callback", "callbackFilter");
		map.put("/**", "user");
		return map;
	}

	@Bean
	public Config pac4jConfig() {
		Config config = new Config();
		config.setClients(pac4jClients());
		return config;
	}

	@Bean
	public Clients pac4jClients() {
		Clients clients = new Clients();
		clients.setCallbackUrl("http://127.0.0.1:8081/login_callback");
		clients.setClients(casClient());
		return clients;
	}

	@Bean
	public CasClient casClient() {
		CasClient result = new CasClient();
		CasConfiguration config = new CasConfiguration();
		config.setLoginUrl("https://cas.server.name:8443/cas/");
		result.setConfiguration(config);
		return result;
	}

	public Map<String, Filter> filters() {
		Map<String, Filter> r = Maps.newHashMap();
		//callback
		CallbackFilter callbackFilter = new CallbackFilter();
		callbackFilter.setConfig(pac4jConfig());
		callbackFilter.setMultiProfile(true);
		r.put("callbackFilter", callbackFilter);
		//cas
		SecurityFilter casFilter = new SecurityFilter();
		casFilter.setClients("CasClient");
		casFilter.setConfig(pac4jConfig());
		r.put("casSecurityFilter", casFilter);
		return r;
	}
}
