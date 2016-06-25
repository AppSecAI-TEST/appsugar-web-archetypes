package org.appsugar;

import org.appsugar.controller.ControllerConfiguration;
import org.springframework.boot.SpringApplication;

/**
 * 程序启动入口
 * @author NewYoung
 * 2016年6月25日下午1:59:09
 */
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(new Object[] { ControllerConfiguration.class, SecurityConfiguration.class,
				ResourceConfiguration.class, ServiceConfiguration.class, RepositoryConfiguration.class }, args);
	}
}
