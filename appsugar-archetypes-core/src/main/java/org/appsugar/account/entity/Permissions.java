package org.appsugar.account.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appsugar.entity.KeyValue;

/**
 * 权限集合
 * @author NewYoung
 * 2016年2月23日下午6:10:16
 */
public enum Permissions {
	ALL("所有权限", "*", "admin")//超级管理员
	//用户权限
	, USER_ALL("用户所有权限", "user:*", "user"), USER_VIEW("查看用户", "user:view", "user"), USER_EDIT("修改用户", "user:edit", "user"), USER_REMOVE("删除用户", "user:remove", "user")//
	//角色权限
	, ROLE_ALL("角色所有权限", "role:*", "role"), ROLE_VIEW("角色查看", "role:view", "role"), ROLE_EDIT("角色修改", "role:edit", "role"), ROLE_REMOVE("角色删除", "role:remove", "role")//
	;
	//权限显示名称
	public final String name;
	//权限值
	public final String permission;
	//权限组
	public final String group;

	private Permissions(String name, String permission, String group) {
		this.name = name;
		this.permission = permission;
		this.group = group;
	}

	private Permissions(String name, String permission) {
		this(name, permission, "default");
	}

	public String getName() {
		return name;
	}

	public String getPermission() {
		return permission;
	}

	public String getGroup() {
		return group;
	}

	private static List<KeyValue<String, List<Permissions>>> permissionsGroupList;

	static {
		List<Permissions> permissionsList = Arrays.asList(Permissions.values());
		permissionsGroupList = new ArrayList<>();
		Map<String, KeyValue<String, List<Permissions>>> groupPermission = new HashMap<>();
		for (Permissions permission : permissionsList) {
			String group = permission.group;
			KeyValue<String, List<Permissions>> keyValue = groupPermission.get(group);
			if (keyValue == null) {
				keyValue = new KeyValue<String, List<Permissions>>(group, new ArrayList<Permissions>());
				permissionsGroupList.add(keyValue);
				groupPermission.put(group, keyValue);
			}
			keyValue.getValue().add(permission);
		}
	}

	public static List<KeyValue<String, List<Permissions>>> getPermissionList() {
		return permissionsGroupList;
	}
}
