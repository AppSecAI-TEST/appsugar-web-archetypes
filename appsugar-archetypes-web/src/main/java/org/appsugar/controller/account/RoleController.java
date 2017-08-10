package org.appsugar.controller.account;

import java.util.Objects;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.controller.BaseController;
import org.appsugar.domain.Response;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.condition.RoleCondition;
import org.appsugar.service.account.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/role")
public class RoleController extends BaseController {

	@Autowired
	RoleService roleService;

	@ModelAttribute
	public Role modelAttribute(Long id) {
		return Objects.isNull(id) ? new Role() : roleService.get(id);
	}

	@GetMapping
	@RequiresPermissions(Role.permission_view)
	public Response<Page<Role>> list(RoleCondition condition, Pageable pageable) {
		logger.debug("find role by condition {}", condition);
		return Response.success(roleService.getByCondition(condition, pageable));
	}

	@RequiresPermissions(Role.permission_view)
	@GetMapping("form")
	public Response<Role> form(@ModelAttribute Role role) {
		return Response.success(role);
	}

	@RequiresPermissions(Role.permission_edit)
	@RequestMapping("save")
	public Response<Void> save(@Valid Role role, BindingResult result) {
		if (result.hasErrors()) {
			return onBindException(result);
		}
		roleService.save(role);
		return Response.SUCCESS;
	}

}
