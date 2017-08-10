package org.appsugar.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.subject.Subject;
import org.appsugar.domain.Menu;
import org.appsugar.domain.MenuGroup;
import org.appsugar.security.ShiroUtils;
import org.appsugar.util.MenuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
