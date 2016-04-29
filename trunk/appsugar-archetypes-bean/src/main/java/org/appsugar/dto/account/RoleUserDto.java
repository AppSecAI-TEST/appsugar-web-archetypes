package org.appsugar.dto.account;

/**
 * 
 * @author NewYoung
 * 2016年2月24日下午4:18:20
 */
public class RoleUserDto {

	private Long roleId;

	private String roleName;

	private String roleTitle;

	private Long userCount;

	public RoleUserDto(Long roleId, String roleName, String roleTitle, Long userCount) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleTitle = roleTitle;
		this.userCount = userCount;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleTitle() {
		return roleTitle;
	}

	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}

	public Long getUserCount() {
		return userCount;
	}

	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleUserDto [roleId=");
		builder.append(roleId);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", roleTitle=");
		builder.append(roleTitle);
		builder.append(", userCount=");
		builder.append(userCount);
		builder.append("]");
		return builder.toString();
	}

}
