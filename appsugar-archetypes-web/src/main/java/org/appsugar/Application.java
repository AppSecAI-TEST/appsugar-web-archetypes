package org.appsugar;

import org.appsugar.controller.ControllerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * 程序启动入口
 * @author NewYoung
 * 2016年6月25日下午1:59:09
 */
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(getResources());
	}

	public static void main(String[] args) {
		SpringApplication.run(getResources(), args);
	}

	public static Object[] getResources() {
		return new Object[] { ControllerConfiguration.class, SecurityConfiguration.class, ResourceConfiguration.class,
				ServiceConfiguration.class, RepositoryConfiguration.class };
	}
}
