package org.appsugar.entity.account;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.appsugar.bean.entity.LongIdEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色
 * @author NewYoung
 * 2016年2月23日下午5:50:59
 */
@Entity
@Table(name = "as_role")
public class Role extends LongIdEntity {
	private static final long serialVersionUID = 8151602082162251470L;
	//permission
	public static final String permission_all = "role:*";
	public static final String permission_view = "role:view";
	public static final String permission_edit = "role:edit";
	public static final String permission_remove = "role:remove";

	//角色名
	@NotBlank
	private String name;
	//角色标题
	@NotBlank
	private String title;
	//角色权限
	private Set<String> permissionList;

	public Role() {
		super();
	}

	public Role(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}

	@Column(name = "name", unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "as_role_permission", joinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "permission")
	public Set<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(Set<String> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [name=");
		builder.append(name);
		builder.append(", title=");
		builder.append(title);
		builder.append(", permissionList=");
		builder.append(permissionList);
		builder.append(", id=");
		builder.append(id);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}
