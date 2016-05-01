package org.appsugar.controller.account;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.condition.account.UserCondition;
import org.appsugar.controller.account.dto.UserDto;
import org.appsugar.entity.account.User;
import org.appsugar.repository.extend.PageAdapter;
import org.appsugar.service.account.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

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
	private UserService userService;

	@RequiresPermissions("user:view")
	@RequestMapping
	public String list(Model model, UserCondition condition, PageAdapter adapter) {
		adapter.setPageSize(5);
		model.addAttribute("page", userService.getByCondition(condition, adapter.toPageable()));
		model.addAttribute("condition", condition);
		return "/account/user/list";
	}

	@RequiresPermissions("user:view")
	@RequestMapping("listUser")
	@ResponseBody
	public DeferredResult<List<UserDto>> listUser() {
		final DeferredResult<List<UserDto>> async = new DeferredResult<>();
		userService.getAllAsync().addCallback(new ListenableFutureCallback<List<User>>() {
			@Override
			public void onSuccess(List<User> result) {
				async.setResult(UserDto.transfer(result));
			}

			@Override
			public void onFailure(Throwable ex) {
				async.setErrorResult(ex);
			}
		});
		return async;
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
