package org.appsugar.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.appsugar.domain.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.MoreObjects;
import com.google.common.base.Throwables;

/**
 * 登录控制器
 * @author NewYoung
 * 2016年3月2日下午12:02:37
 */
@RestController
public class LoginController {

	/**
	 * 用户登录
	 * @param username 账号
	 * @param password 密码
	 * @param rememberMe 是否记住自己
	 */
	@RequestMapping("/login")
	public Response<Void> login(@RequestParam String username, @RequestParam String password, Boolean rememberMe) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password,
				MoreObjects.firstNonNull(rememberMe, Boolean.FALSE));
		try {
			subject.login(token);
		} catch (AuthenticationException ex) {
			return Response.error("username or password error " + Throwables.getRootCause(ex).getMessage());
		}
		return Response.SUCCESS;
	}

}
