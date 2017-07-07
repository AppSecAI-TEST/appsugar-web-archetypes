package org.appsugar.entity.account;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.appsugar.bean.entity.LongIdEntity;

/**
 * 用户
 * @author NewYoung
 * 2016年2月23日下午6:08:56
 */
@Entity
@Table(name = "as_user", indexes = { @Index(columnList = "phone"), @Index(columnList = "email") })
public class User extends LongIdEntity {

	private static final long serialVersionUID = 1250833677334835146L;
	//permission
	public static final String permission_all = "user:*";
	public static final String permission_view = "user:view";
	public static final String permission_edit = "user:edit";
	public static final String permission_remove = "user:remove";

	/**名称**/
	private String name;
	/**安全手机号**/
	private String phone;
	/**安全邮箱地址**/
	private String email;
	/**性别**/
	private Gender gender = Gender.UNSPECIFIED;
	/**角色**/
	private List<Role> roleList;
	/**权限**/
	private List<String> permissionList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", createdAt=").append(createdAt).append(", updatedAt=")
				.append(updatedAt).append("]");
		return builder.toString();
	}

}
