package org.appsugar.account.service.impl;

import org.appsugar.account.condition.RoleCondition;
import org.appsugar.account.entity.Role;
import org.appsugar.account.service.RoleService;
import org.appsugar.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, RoleCondition> implements RoleService {
}
