package org.appsugar.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.appsugar.controller.domain.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
@ControllerAdvice
public class MainController {

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
}
