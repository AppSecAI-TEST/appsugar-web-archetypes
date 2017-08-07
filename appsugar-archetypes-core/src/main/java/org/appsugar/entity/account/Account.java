package org.appsugar.entity.account;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.appsugar.bean.entity.LongIdEntity;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 用户账号
 * @author NewYoung
 * 2016年12月27日上午9:50:44
 */
@Entity
@Table(name = "as_account", uniqueConstraints = @UniqueConstraint(columnNames = { "key", "secret" }))
@DynamicUpdate
public class Account extends LongIdEntity {
	private static final long serialVersionUID = -7658886817003402072L;

	public static final String _user = "user";

	/**对应用户**/
	private User user;
	/**profile**/
	private AccountProfile profile;
	/**类型**/
	private AccountType type;
	/**键**/
	private String key;
	/**秘钥**/
	private String secret;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Embedded
	public AccountProfile getProfile() {
		return profile;
	}

	public void setProfile(AccountProfile profile) {
		this.profile = profile;
	}

	@Enumerated(EnumType.STRING)
	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [profile=").append(profile).append(", type=").append(type).append(", key=").append(key)
				.append(", secret=").append(secret).append(", id=").append(id).append(", createdAt=").append(createdAt)
				.append(", updatedAt=").append(updatedAt).append("]");
		return builder.toString();
	}

}
