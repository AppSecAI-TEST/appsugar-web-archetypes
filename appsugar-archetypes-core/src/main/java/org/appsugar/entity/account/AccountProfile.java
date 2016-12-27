package org.appsugar.entity.account;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 账户信息
 * @author NewYoung
 * 2016年12月27日上午9:54:49
 */
public class AccountProfile {
	/**显示名称**/
	private String name;
	/**邮件**/
	private String email;
	/**图像地址**/
	private String pictureUrl;
	/**性别**/
	private Gender gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "picture_url")
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountProfile [name=").append(name).append(", email=").append(email).append(", pictureUrl=")
				.append(pictureUrl).append(", gender=").append(gender).append("]");
		return builder.toString();
	}

}
