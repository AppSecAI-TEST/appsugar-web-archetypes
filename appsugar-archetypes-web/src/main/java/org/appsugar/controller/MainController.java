package org.appsugar.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.appsugar.domain.Menu;
import org.appsugar.domain.MenuGroup;
import org.appsugar.domain.Response;
import org.appsugar.security.ShiroUtils;
import org.appsugar.util.MenuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.MoreObjects;
import com.google.common.base.Throwables;

/**
 *
 * @author NewYoung
 * 2016年3月1日下午5:20:19
 */
@Controller
public class MainController {

	/**
	 * 查看我的菜单
	 */
	@GetMapping("/menu")
	@ResponseBody
	public List<MenuGroup> menu() {
		Subject self = ShiroUtils.getSubject();
		List<MenuGroup> result = new ArrayList<>();
		for (MenuGroup group : MenuUtils.getCachedMenuGroup()) {
			List<Menu> menus = new ArrayList<>();
			for (Menu menu : group.getMenus()) {
				if (self.isPermittedAll(menu.getPermissionsArray())) {
					menus.add(menu);
				}
			}
			if (menus.isEmpty()) {
				continue;
			}
			MenuGroup newGroup = new MenuGroup(group.getName(), group.getCode(), menus);
			result.add(newGroup);
		}
		return result;
	}

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
