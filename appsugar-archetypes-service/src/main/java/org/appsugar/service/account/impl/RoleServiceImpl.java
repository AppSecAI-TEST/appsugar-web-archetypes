package org.appsugar.service.account.impl;

import org.appsugar.condition.account.RoleCondition;
import org.appsugar.entity.account.Role;
import org.appsugar.service.account.RoleService;
import org.appsugar.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, RoleCondition> implements RoleService {

	public RoleServiceImpl() {
		super();
		System.out.println("123w");
	}

}
