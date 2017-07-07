package org.appsugar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 程序启动入口
 * @author NewYoung
 * 2016年6月25日下午1:59:09
 */
@RestController
@EnableAutoConfiguration
@EnableJpaRepositories
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(getResources());
	}

	public static void main(String[] args) {
		SpringApplication.run(getResources(), args);
	}

	public static Object[] getResources() {
		return new Object[] { Application.class };
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
}
