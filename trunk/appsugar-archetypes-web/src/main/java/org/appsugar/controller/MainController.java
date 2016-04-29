package org.appsugar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author NewYoung
 * 2016年3月1日下午5:20:19
 */
@Controller
public class MainController {

	/**
	 * 转换至首页
	 */
	@RequestMapping("/main")
	public String main() {
		return "/index";
	}

}
