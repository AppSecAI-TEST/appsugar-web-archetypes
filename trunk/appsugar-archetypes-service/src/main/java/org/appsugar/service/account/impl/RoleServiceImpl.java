package org.appsugar.service.account.impl;

import org.appsugar.condition.account.RoleCondition;
import org.appsugar.entity.account.Role;
import org.appsugar.repository.account.RoleRepository;
import org.appsugar.service.account.RoleService;
import org.appsugar.service.impl.GenericServiceImpl;
import org.appsugar.specification.account.RoleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<Role> getByCondition(RoleCondition condition, Pageable pageable) {
		return roleRepository.findAll(new RoleSpecification(condition), pageable);
	}

}
