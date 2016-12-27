package org.appsugar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.appsugar.controller.shiro.FormAuthenticator;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.oauth.client.CasOAuthWrapperClient;
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

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, Config config) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);
		filter.setLoginUrl("/login/form");
		filter.setSuccessUrl("/main");
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap());
		filter.setFilters(filters(config));
		return filter;
	}

	public Map<String, String> filterChainDefinitionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("/favicon.ico", "anon");
		map.put("/static/**", "anon");
		map.put("/assets/**", "anon");
		map.put("/webjars/**", "anon");
		map.put("/logout", "logout");
		map.put("/login", "formSecurityFilter");
		map.put("/cas_login", "casSecurityFilter");
		map.put("/login_callback", "callbackFilter");
		map.put("/oauth2_login", "oauth2SecurityFilter");
		map.put("/**", "user");
		return map;
	}

	@Bean
	public Config pac4jConfig(Clients clients) {
		Config config = new Config();
		config.setClients(clients);
		return config;
	}

	@Bean
	public Clients pac4jClients(FormClient formClient) {
		Clients clients = new Clients();
		clients.setCallbackUrl("http://127.0.0.1:8081/login_callback");
		clients.setClients(casClient(), casOAuthWrapperClient(), formClient);
		return clients;
	}

	/**
	 * cas单点
	 * @author NewYoung
	 * 2016年12月26日下午5:11:24
	 */
	@Bean
	public CasClient casClient() {
		CasClient result = new CasClient();
		CasConfiguration config = new CasConfiguration();
		config.setLoginUrl("https://cas.server.name:8443/cas/");
		result.setConfiguration(config);
		return result;
	}

	/**
	 * oauth2 授权
	 * @author NewYoung
	 * 2016年12月26日下午5:11:28
	 */
	@Bean
	public CasOAuthWrapperClient casOAuthWrapperClient() {
		CasOAuthWrapperClient client = new CasOAuthWrapperClient();
		client.setCasOAuthUrl("https://cas.server.name:8443/cas/oauth2.0");
		client.setKey("test");
		client.setSecret("test");
		return client;
	}

	/**
	 * form方式登录
	 * @author NewYoung
	 * 2016年12月26日下午5:15:23
	 */
	@Bean
	public FormClient formClient(FormAuthenticator authenticator) {
		FormClient client = new FormClient();
		client.setLoginUrl("/login/form");
		client.setAuthenticator(authenticator);
		return client;
	}

	public Map<String, Filter> filters(Config config) {
		Map<String, Filter> r = Maps.newHashMap();
		//callback
		CallbackFilter callbackFilter = new CallbackFilter();
		callbackFilter.setConfig(config);
		callbackFilter.setMultiProfile(true);
		r.put("callbackFilter", callbackFilter);
		//cas
		SecurityFilter casFilter = new SecurityFilter();
		casFilter.setClients("CasClient");
		casFilter.setConfig(config);
		r.put("casSecurityFilter", casFilter);
		//oauth2
		SecurityFilter oauth2Filter = new SecurityFilter();
		oauth2Filter.setClients("CasOAuthWrapperClient");
		oauth2Filter.setConfig(config);
		r.put("oauth2SecurityFilter", oauth2Filter);
		//form
		SecurityFilter formFilter = new SecurityFilter();
		formFilter.setClients("FormClient");
		formFilter.setConfig(config);
		r.put("formSecurityFilter", formFilter);
		return r;
	}
}
