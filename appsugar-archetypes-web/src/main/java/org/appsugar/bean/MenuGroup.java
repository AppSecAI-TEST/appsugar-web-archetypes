package org.appsugar.bean;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 
 * @author NewYoung
 * 2016年3月1日下午4:42:29
 */
public class MenuGroup extends MenuConfig {

	public MenuGroup() {
		super();
	}

	public MenuGroup(String url, String name, String code) {
		super(url, name, code);
	}

	public MenuGroup(String url, String name, String code, String role, String permission) {
		super(url, name, code, role, permission);
	}

	//子菜单
	private List<MenuConfig> menuList = Lists.newArrayList();

	public List<MenuConfig> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuConfig> menuList) {
		this.menuList = menuList;
	}

	public void addChild(MenuConfig menu) {
		menuList.add(menu);
	}

	/**
	 * 判断当前用户是否能够查看这个菜单组
	 */
	@Override
	public boolean isShow() {
		for (MenuConfig menuConfig : menuList) {
			if (menuConfig.isShow()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuGroup [menuList=").append(menuList).append("]");
		return builder.toString();
	}

}
