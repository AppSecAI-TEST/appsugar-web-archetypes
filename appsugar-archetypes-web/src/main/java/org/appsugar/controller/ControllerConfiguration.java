package org.appsugar.controller;

import java.util.Arrays;
import java.util.Properties;

import javax.servlet.DispatcherType;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.appsugar.controller.shiro.Pac4jRealm;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ro.isdc.wro.http.WroFilter;

/**
 * controller层配置
 * @author NewYoung
 * 2016年6月25日上午10:23:54
 */
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
public class ControllerConfiguration extends WebMvcConfigurerAdapter {

	public static final String viewPrefix = "/WEB-INF/views";
	public static final String viewSuffix = ".jsp";

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/").setViewName("redirect:/main");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
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
	 * 异常解析
	 */
	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties p = new Properties();
		p.setProperty("org.apache.shiro.authz.UnauthorizedException", "error/403");
		resolver.setExceptionMappings(p);
		return resolver;
	}

	/**
	 * 路径解析
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(viewPrefix);
		resolver.setSuffix(viewSuffix);
		return resolver;
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
	 * sitemesh filter
	 */
	@Bean
	public FilterRegistrationBean sitemeshFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new ConfigurableSiteMeshFilter());
		bean.setUrlPatterns(Arrays.asList("/*"));
		bean.setAsyncSupported(true);
		bean.setName("sitemeshFilter");
		bean.setOrder(4);
		return bean;
	}

	/**
	 * wroFilter
	 */
	@Bean
	public FilterRegistrationBean wroFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WroFilter());
		bean.setUrlPatterns(Arrays.asList("/assets/*"));
		bean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
		bean.setAsyncSupported(true);
		bean.setOrder(5);
		bean.setName("wroFilter");
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
	public DefaultWebSecurityManager securityManager(Pac4jRealm shiroRealm, CacheManager shiroCacheManager) {
		DefaultWebSecurityManager stm = new DefaultWebSecurityManager();
		stm.setRealms(Arrays.asList(shiroRealm));
		stm.setCacheManager(shiroCacheManager);
		return stm;
	}
}
