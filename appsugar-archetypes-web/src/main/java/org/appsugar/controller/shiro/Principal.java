package org.appsugar.controller.shiro;

import java.io.Serializable;
import java.util.List;

import org.appsugar.entity.account.Account;

/**
 * 自定义principal
 * @author NewYoung
 * 2016年12月27日下午1:24:40
 */
public class Principal implements Serializable {
	private static final long serialVersionUID = -8422899369911795302L;

	private List<Account> accountList;

	public Principal(List<Account> accountList) {
		super();
		this.accountList = accountList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
}
