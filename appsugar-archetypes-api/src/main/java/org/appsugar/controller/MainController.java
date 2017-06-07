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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;

/**
 * 登录控制器
 * @author NewYoung
 * 2016年3月2日下午12:02:37
 */
@Controller
@RequestMapping
@ControllerAdvice
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Response<Void> login(@RequestParam("username") String username, @RequestParam("password") String password,
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
	public Response<Void> loginout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return Response.SUCCESS;
	}

	/**
	 * 用户未授权
	 */
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public Response<Void> onAuthorization() {
		return Response.UN_AUTHORIZATION;
	}

	/**
	 * 服务器内异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response<Void> onException(Exception ex) {
		logger.error("internal exception ", ex);
		return Response.error(Throwables.getRootCause(ex).getMessage());
	}
}
