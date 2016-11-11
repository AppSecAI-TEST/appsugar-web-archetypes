package org.appsugar.entity.account.condition;

import org.appsugar.bean.condition.LongIdEntityCondition;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午2:27:30
 */
public class UserCondition extends LongIdEntityCondition {

	//名称 start like
	private String name;
	//登陆名称 eq
	private String loginName;

	public UserCondition() {
		super();
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
				.append(id).append(", startAt=").append(startAt).append(", endAt=").append(endAt).append("]");
		return builder.toString();
	}

}
