package org.appsugar.entity.account.condition;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.entity.account.AccountType;

/**
 * 账户查询条件
 * @author NewYoung
 * 2016年12月27日上午11:10:03
 */
public class AccountCondition extends LongIdEntityCondition {
	private static final long serialVersionUID = -1703091422137097882L;
	/**键 eq**/
	private String key;
	/**秘钥 eq**/
	private String secret;
	/**账户类型 eq**/
	private AccountType accountType;

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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountCondition [key=").append(key).append(", secret=").append(secret).append(", accountType=")
				.append(accountType).append("]");
		return builder.toString();
	}

}
