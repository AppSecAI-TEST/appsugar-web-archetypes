package org.appsugar.entity.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.appsugar.bean.domain.KeyValue;

/**
 * 权限集合
 * @author NewYoung
 * 2016年2月23日下午6:10:16
 */
public enum Permissions {
	ALL("所有权限", "*", "admin")//超级管理员
	/**用户权限**/
	,USER_ALL("用户所有权限", User.permission_all, "user"), USER_VIEW("查看用户", User.permission_view, "user"), USER_EDIT("用户修改",
			User.permission_edit, "user"), USER_REMOVE("用户删除", User.permission_remove, "user")//
	/**角色权限**/
	,ROLE_ALL("角色所有权限", Role.permission_all, "role"), ROLE_VIEW("角色查看", Role.permission_view, "role"), ROLE_EDIT("角色修改",
			Role.permission_edit, "role"), ROLE_REMOVE("角色删除", Role.permission_remove, "role");//;
	/*****************************************************************************************************/
	/**权限显示名称**/
	public final String name;
	/**权限值**/
	public final String permission;
	/**权限组**/
	public final String group;
	/**权限依赖**/
	public final List<Permissions> dependencies;

	private Permissions(String name, String permission, String group, Permissions... dependencies) {
		this.name = name;
		this.permission = permission;
		this.group = group;
		this.dependencies = Collections
				.unmodifiableList(dependencies == null ? Collections.emptyList() : Arrays.asList(dependencies));
	}

	private Permissions(String name, String permission, String group) {
		this(name, permission, group, (Permissions[]) null);
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

	private static Map<String, Permissions> groupByPermission;
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
		groupByPermission = Arrays.stream(Permissions.values())
				.collect(Collectors.toMap(Permissions::getPermission, e -> e));
	}

	public static List<KeyValue<String, List<Permissions>>> getPermissionList() {
		return permissionsGroupList;
	}

	/**
	 * 根据权限获取到权限定义枚举
	 * 没有就返回null
	 */
	public static Permissions getByPermissionString(String permission) {
		return groupByPermission.get(permission);
	}
}
