package org.appsugar.account.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.appsugar.entity.IdEntity;
import org.hibernate.validator.constraints.Email;

/**
 * 用户
 * @author NewYoung
 * 2016年2月23日下午6:08:56
 */
@Entity
@Table(name = "as_user")
public class User extends IdEntity {

	private static final long serialVersionUID = 1250833677334835146L;
	public static final String _name = "name";
	public static final String _loginName = "loginName";
	public static final String _password = "password";
	public static final String _phone = "phone";
	public static final String _email = "email";
	public static final String _roleList = "roleList";
	public static final String _permissionList = "permissionList";

	//名称
	private String name;
	//登录名
	private String loginName;
	//密码
	private String password;
	//电话
	private String phone;
	//邮箱
	private String email;

	private Role testRole;
	//角色
	private List<Role> roleList;
	//权限
	private List<String> permissionList;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "login_name", unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "as_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "as_user_permission", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "permission")
	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getTestRole() {
		return testRole;
	}

	public void setTestRole(Role testRole) {
		this.testRole = testRole;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=").append(name).append(", loginName=").append(loginName).append(", password=")
				.append(password).append(", phone=").append(phone).append(", email=").append(email).append(", id=")
				.append(id).append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt)
				.append("]");
		return builder.toString();
	}

}
