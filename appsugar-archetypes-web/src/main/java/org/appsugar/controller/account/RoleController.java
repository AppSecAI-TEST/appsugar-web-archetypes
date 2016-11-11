package org.appsugar.controller.account;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.entity.account.Permissions;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.condition.RoleCondition;
import org.appsugar.service.account.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	RoleService roleService;

	@ModelAttribute
	public Role modelAttribute(Long id) {
		if (id == null) {
			return new Role();
		}
		Role role = roleService.get(id);
		return role != null ? role : new Role();
	}

	@RequestMapping
	@RequiresPermissions(Role.permission_view)
	public String list(Model model, RoleCondition condition, Pageable pageable) {
		model.addAttribute("page", roleService.getByCondition(condition, pageable));
		return "/account/role/list";
	}

	@RequiresPermissions(Role.permission_view)
	@RequestMapping("form")
	public String form(Model model, @ModelAttribute Role role) {
		model.addAttribute("role", role);
		model.addAttribute("permissions", Permissions.getPermissionList());
		return "/account/role/form";
	}

	@RequiresPermissions(Role.permission_edit)
	@RequestMapping("save")
	public String save(@Valid Role role, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		logger.debug("role {} {}", role.hashCode(), role.getPermissionList());
		if (result.hasErrors()) {
			return form(model, role);
		}
		roleService.save(role);
		redirectAttributes.addFlashAttribute("message", "创建角色" + role.getName() + "成功");
		return "redirect:/account/role";
	}

}
