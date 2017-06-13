package org.appsugar.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 资源配置
 * @author NewYoung
 * 2016年6月25日上午11:03:22
 */
@Configuration
public class ResourceConfiguration {

	/**
	 * 国际化消息
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setFallbackToSystemLocale(false);
		source.setBasenames("messages/ApplicationResources");
		return source;
	}
}
