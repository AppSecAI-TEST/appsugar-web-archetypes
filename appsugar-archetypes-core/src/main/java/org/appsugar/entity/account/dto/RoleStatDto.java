package org.appsugar.entity.account.dto;

public class RoleStatDto {
	/**角色ID**/
	private Long id;
	/**角色名称**/
	private String name;
	/**用户数量**/
	private Long users;

	public RoleStatDto(Long id, String name, Long users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUsers() {
		return users;
	}

	public void setUsers(Long users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleStatDto [id=").append(id).append(", name=").append(name).append(", users=").append(users)
				.append("]");
		return builder.toString();
	}

}
