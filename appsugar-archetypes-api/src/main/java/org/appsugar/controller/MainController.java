package org.appsugar.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.appsugar.controller.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录控制器
 * @author NewYoung
 * 2016年3月2日下午12:02:37
 */
@Controller
@RequestMapping
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Response login(String username, String password,
			@RequestParam(defaultValue = "false", required = false) Boolean rememberMe) throws AuthenticationException {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		try {
			subject.login(token);
		} catch (@SuppressWarnings("unused") AuthenticationException ex) {
			return Response.error("username or password error");
		}
		return Response.SUCCESS;
	}

	/**
	 * 用户登出
	 */
	@RequestMapping(value = "loginout")
	@ResponseBody
	public Response loginout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return Response.SUCCESS;
	}

	/**
	 * 用户未认证 
	 */
	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	public Response onAuthenticationException() {
		return Response.UN_AUTHENTICATION;
	}

	/**
	 * 用户未授权
	 */
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public Response onAuthorization() {
		return Response.UN_AUTHORIZATION;
	}

	/**
	 * 服务器内异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response onException(Exception ex) {
		logger.debug("internal exceptioin ", ex);
		return Response.ERROR;
	}
}
