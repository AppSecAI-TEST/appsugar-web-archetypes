package org.appsugar.controller.system;

import static org.appsugar.util.BeanCopyUtils.map;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.controller.BaseController;
import org.appsugar.domain.Response;
import org.appsugar.entity.account.Account;
import org.appsugar.entity.account.QAccount;
import org.appsugar.entity.account.QUser;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.appsugar.service.account.AccountService;
import org.appsugar.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 * @author NewYoung
 * 2016年2月29日上午11:32:33
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;

	/**
	 * 查询用户
	 */
	@RequiresPermissions(User.permission_view)
	@GetMapping
	public Response<Page<User>> list(UserCondition condition, Pageable pageable) {
		logger.debug("find user by condition {}", condition);
		return Response.success(transfer(userService.getByCondition(condition, pageable),
				u -> map(u, new User(), QUser.user.roleList)));
	}

	/**
	 *查看用户详情 
	 */
	@RequiresPermissions(User.permission_view)
	@GetMapping("detail/{id}")
	public Response<User> details(@PathVariable Long id) {
		return Response.success(userService.get(id));
	}

	/**
	 * 查看用户账户
	 */
	@RequiresPermissions(User.permission_view)
	@GetMapping("accounts/{id}")
	public Response<List<Account>> accounts(@PathVariable Long id) {
		QAccount q = QAccount.account;
		return Response.success(
				transfer(accountService.getByUserId(id), a -> map(a, new Account(), q.user).setSecret("******")));
	}
}
