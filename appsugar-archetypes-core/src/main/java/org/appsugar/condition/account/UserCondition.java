package org.appsugar.condition.account;

import java.util.Date;

import org.appsugar.condition.IdEntityCondition;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午2:27:30
 */
public class UserCondition extends IdEntityCondition {

	//名称 start like
	private String name;
	//登陆名称 eq
	private String loginName;

	public UserCondition() {
		super();
	}

	public UserCondition(Long id, Date createdStart, Date createdEnd, String name, String loginName) {
		super(id, createdStart, createdEnd);
		this.name = name;
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserCondition [name=").append(name).append(", loginName=").append(loginName).append(", id=")
				.append(id).append(", createdStart=").append(createdStart).append(", createdEnd=").append(createdEnd)
				.append("]");
		return builder.toString();
	}

}
