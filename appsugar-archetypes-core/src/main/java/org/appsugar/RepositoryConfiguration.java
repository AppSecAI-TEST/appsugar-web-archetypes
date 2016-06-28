package org.appsugar;

import org.appsugar.repository.IdEntityRepositoryImpl;
import org.appsugar.repository.RepositoryPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 配置bean
 * @author NewYoung
 * 2016年6月24日下午7:02:38
 */
@EnableAsync
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = IdEntityRepositoryImpl.class)
public class RepositoryConfiguration {

	/**
	 * repository 增强
	 * @return
	 */
	@Bean
	public RepositoryPostProcessor reposiotryAdvance() {
		return new RepositoryPostProcessor();
	}

}
