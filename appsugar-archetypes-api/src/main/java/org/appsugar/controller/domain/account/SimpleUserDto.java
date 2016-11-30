package org.appsugar.controller.domain.account;

import org.appsugar.controller.domain.BaseEntityDto;

/**
 * 用户简单数据dto
 * @author NewYoung
 * 2016年11月30日下午4:15:21
 */
public class SimpleUserDto extends BaseEntityDto {
	private String name;
	private String phone;
	private String email;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimpleUserDto [id=").append(id).append(", createdAt=").append(createdAt).append(", updatedAt=")
				.append(updatedAt).append(", name=").append(name).append(", phone=").append(phone).append(", email=")
				.append(email).append("]");
		return builder.toString();
	}

}
