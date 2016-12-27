package org.appsugar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(method = RequestMethod.GET, value = "form")
	public String login() {
		return "/account/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(Model model) {
		model.addAttribute("login_error", true);
		return "/account/login";
	}
}
