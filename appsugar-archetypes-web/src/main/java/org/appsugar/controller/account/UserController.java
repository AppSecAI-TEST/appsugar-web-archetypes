package org.appsugar.controller.account;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.controller.BaseController;
import org.appsugar.domain.Response;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.appsugar.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 * @author NewYoung
 * 2016年2月29日上午11:32:33
 */
@RestController
@RequestMapping("/account/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@RequiresPermissions(User.permission_view)
	@GetMapping
	public Response<Page<User>> list(UserCondition condition, Pageable pageable) {
		logger.debug("find user by condition {}", condition);
		return Response.success(userService.getByCondition(condition, pageable));
	}
}
