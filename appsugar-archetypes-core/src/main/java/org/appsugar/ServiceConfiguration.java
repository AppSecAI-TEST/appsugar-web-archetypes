package org.appsugar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * 配置事物与shiro
 * @author NewYoung
 * 2016年6月24日下午7:02:38
 */

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource("classpath:/applicationContext-service.xml")
public class ServiceConfiguration {

}
