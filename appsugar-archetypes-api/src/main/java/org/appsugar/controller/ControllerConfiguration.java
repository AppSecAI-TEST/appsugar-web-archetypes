package org.appsugar.controller;

import java.util.Arrays;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.appsugar.controller.security.ShiroRealm;
import org.modelmapper.ModelMapper;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * controller层配置
 * @author NewYoung
 * 2016年6月25日上午10:23:54
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
public class ControllerConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * 数据拷贝
	 * @author NewYoung
	 * 2016年11月30日下午4:01:41
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 保证shiro验证
	 */
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	/**
	 * security filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean delegatingFilterProxy() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new DelegatingFilterProxy());
		bean.setUrlPatterns(Arrays.asList("/*"));
		bean.setName("shiroFilter");
		bean.addInitParameter("targetFilterLifecycle", "true");
		bean.setAsyncSupported(true);
		bean.setOrder(1);
		return bean;
	}

	@Bean
	public FilterRegistrationBean openEntityManagerInViewFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new OpenEntityManagerInViewFilter());
		bean.setUrlPatterns(Arrays.asList("/*"));
		bean.setAsyncSupported(true);
		bean.setName("openEntityManagerInViewFilter");
		bean.setOrder(2);
		return bean;
	}

	/**
	 * 编码filter
	 */
	@Bean
	public FilterRegistrationBean characterEncodingFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new CharacterEncodingFilter());
		bean.setUrlPatterns(Arrays.asList("/*"));
		bean.addInitParameter("encoding", "UTF-8");
		bean.addInitParameter("forceEncoding", "true");
		bean.setAsyncSupported(true);
		bean.setName("characterEncodingFilter");
		bean.setOrder(3);
		return bean;
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

	/**
	 * shiro 安全管理
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, CacheManager shiroCacheManager) {
		DefaultWebSecurityManager stm = new DefaultWebSecurityManager();
		stm.setRealms(Arrays.asList(shiroRealm));
		stm.setCacheManager(shiroCacheManager);
		return stm;
	}
}
