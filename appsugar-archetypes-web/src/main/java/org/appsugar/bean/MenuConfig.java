package org.appsugar.bean;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.appsugar.security.shiro.ShiroUtils;

/**
 * 菜单配置
 * @author NewYoung
 * 2016年3月1日下午4:39:32
 */
public class MenuConfig {
	//访问地址
	protected String url;
	//显示名称
	protected String name;
	//国际化代码
	protected String code;
	//角色
	protected String role;
	//权限
	protected String permission;

	public MenuConfig() {
		super();
	}

	public MenuConfig(String url, String name, String code, String role, String permission) {
		super();
		this.url = url;
		this.name = name;
		this.code = code;
		this.role = role;
		this.permission = permission;
	}

	public MenuConfig(String url, String name, String code) {
		super();
		this.url = url;
		this.name = name;
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 判断当前用户是否拥有权限查看该菜单
	 */
	public boolean isShow() {
		Subject subject = ShiroUtils.getSubject();
		if (StringUtils.isBlank(role) && StringUtils.isBlank(permission)) {
			return true;
		}
		if (StringUtils.isNotBlank(permission) && subject.isPermitted(permission)) {
			return true;
		}
		if (StringUtils.isNotBlank(role) && subject.hasRole(role)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuConfig [url=").append(url).append(", name=").append(name).append(", code=").append(code)
				.append(", role=").append(role).append(", permission=").append(permission).append("]");
		return builder.toString();
	}

}
