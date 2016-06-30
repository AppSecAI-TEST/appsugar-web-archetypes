package org.appsugar.account.condition;

import java.util.Date;

import org.appsugar.condition.IdEntityCondition;

public class RoleCondition extends IdEntityCondition {
	//角色名 start like
	private String name;
	//角色标题 start like
	private String title;

	public RoleCondition() {
		super();
	}

	public RoleCondition(Long id, Date createdStart, Date createdEnd, String name, String title) {
		super(id, createdStart, createdEnd);
		this.name = name;
		this.title = title;
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
				.append(", createdStart=").append(createdStart).append(", createdEnd=").append(createdEnd).append("]");
		return builder.toString();
	}

}
