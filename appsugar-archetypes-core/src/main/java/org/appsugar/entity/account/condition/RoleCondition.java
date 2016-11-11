package org.appsugar.entity.account.condition;

import org.appsugar.bean.condition.LongIdEntityCondition;

public class RoleCondition extends LongIdEntityCondition {
	//角色名 start like
	private String name;
	//角色标题 start like
	private String title;

	public RoleCondition() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleCondition [name=").append(name).append(", title=").append(title).append(", id=").append(id)
				.append(", startAt=").append(startAt).append(", endAt=").append(endAt).append("]");
		return builder.toString();
	}

}
