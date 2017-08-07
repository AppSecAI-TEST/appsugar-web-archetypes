package org.appsugar.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录控制器
 * @author NewYoung
 * 2016年3月2日下午12:02:37
 */
@Controller
@RequestMapping("login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return loginAny();
		}
		return "/account/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String loginFailed(HttpServletRequest req) {
		req.setAttribute("msg", "username or password error");
		return "/account/login";
	}

	@RequestMapping("/*")
	public String loginAny() {
		return "redirect:/main";
	}

}
