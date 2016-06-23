package org.appsugar.entity.account;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.appsugar.entity.IdEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色
 * @author NewYoung
 * 2016年2月23日下午5:50:59
 */
@Entity(name = "as_role")
public class Role extends IdEntity {
	private static final long serialVersionUID = 8151602082162251470L;
	public static final String _name = "name";
	public static final String _title = "title";
	public static final String _permissionList = "permissionList";
	public static final String _userList = "userList";

	//角色名
	@NotBlank
	private String name;
	//角色标题
	@NotBlank
	private String title;
	//角色权限
	private List<String> permissionList;
	//角色下所有用户
	private List<User> userList;

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
	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = User._roleList)
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
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
