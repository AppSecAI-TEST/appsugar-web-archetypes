package org.appsugar.controller.account;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.condition.account.UserCondition;
import org.appsugar.dto.page.Pageable;
import org.appsugar.entity.account.User;
import org.appsugar.service.account.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户controller
 * @author NewYoung
 * 2016年2月29日上午11:32:33
 */
@Controller
@RequestMapping("/account/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequiresPermissions("user:view")
	@RequestMapping
	public String list(Model model, UserCondition condition, Pageable pageable) {
		pageable.setPageSize(5);
		model.addAttribute("page", userService.getByCondition(condition, pageable));
		model.addAttribute("condition", condition);
		return "/account/user/list";
	}

	@ModelAttribute
	public User modelAttribute(Long id) {
		if (id == null) {
			return new User();
		}
		User user = userService.get(id);
		return user == null ? new User() : user;
	}

}
