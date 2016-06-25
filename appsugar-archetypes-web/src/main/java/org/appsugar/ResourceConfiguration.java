package org.appsugar;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;

/**
 * 资源配置
 * @author NewYoung
 * 2016年6月25日上午11:03:22
 */
@Configuration
public class ResourceConfiguration {
	public static final String environmentLocation = "/etc/appsugar-archetypes/environment.properties";

	/**
	 * 系统环境
	 */
	@Bean
	public PropertiesFactoryBean environmentProperties() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setIgnoreResourceNotFound(true);
		bean.setLocations(new FileSystemResource(environmentLocation));
		return bean;
	}

	/**
	 * 占位符解析
	 */
	@Bean
	public PropertyPlaceholderConfigurer propertyConfigurer(Properties environmentProperties) {
		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
		configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
		configurer.setProperties(environmentProperties);
		return configurer;
	}

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
