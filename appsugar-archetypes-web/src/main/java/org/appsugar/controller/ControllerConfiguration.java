package org.appsugar.controller;

import java.util.Arrays;

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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/").setViewName("redirect:/static/index.html");
	}

	/**
	 * 路径解析
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		return new InternalResourceViewResolver();
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

}
