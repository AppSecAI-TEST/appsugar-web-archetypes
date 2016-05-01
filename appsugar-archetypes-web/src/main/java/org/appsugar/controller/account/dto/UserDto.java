package org.appsugar.controller.account.dto;

import java.util.ArrayList;
import java.util.List;

import org.appsugar.entity.account.User;

/**
 * 用户数据实体
 * @author NewYoung
 * 2016年2月29日下午2:08:43
 */
public class UserDto {

	private Long id;

	private String name;

	private String loginName;

	public UserDto(String name, String loginName) {
		super();
		this.name = name;
		this.loginName = loginName;
	}

	public UserDto(User user) {
		this.name = user.getName();
		this.loginName = user.getLoginName();
		this.id = user.getId();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDto [id=").append(id).append(", name=").append(name).append(", loginName=")
				.append(loginName).append("]");
		return builder.toString();
	}

	public static List<UserDto> transfer(List<User> userList) {
		List<UserDto> result = new ArrayList<>(userList.size());
		for (User user : userList) {
			result.add(new UserDto(user));
		}
		return result;
	}

}
