package org.appsugar.entity.account.condition;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.entity.account.Gender;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午2:27:30
 */
public class UserCondition extends LongIdEntityCondition {
	private static final long serialVersionUID = 347137554444347578L;
	/**手机号 start like**/
	private String phone;
	/**邮件 start like**/
	private String email;
	/**性别 eq**/
	private Gender gender;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserCondition [phone=").append(phone).append(", email=").append(email).append(", gender=")
				.append(gender).append("]");
		return builder.toString();
	}

}
