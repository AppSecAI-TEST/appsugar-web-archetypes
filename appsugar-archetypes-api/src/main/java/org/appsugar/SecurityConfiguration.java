package org.appsugar;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.appsugar.common.security.ShiroRealm;
import org.appsugar.controller.filter.FormAuthenticationFilterExt;
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

	/**
	 * shiro 安全管理
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, CacheManager shiroCacheManager) {
		DefaultWebSecurityManager stm = new DefaultWebSecurityManager();
		stm.setRealm(shiroRealm);
		stm.setCacheManager(shiroCacheManager);
		return stm;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap());
		Map<String, Filter> filters = Maps.newHashMap();
		filters.put(EXT_AUTHC_FILTER_NAME, new FormAuthenticationFilterExt());
		filter.setFilters(filters);
		return filter;
	}

	/**
	 * 路径映射权限配置 
	 */
	public Map<String, String> filterChainDefinitionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("/login", "anon");
		map.put("/**", EXT_AUTHC_FILTER_NAME);
		return map;
	}
}
