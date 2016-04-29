package org.appsugar.repository.account;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.appsugar.dto.account.RoleUserDto;
import org.appsugar.entity.account.Role;
import org.appsugar.entity.account.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:57:48
 */
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void deleteRole(Long id) {
		userRepository.deleteRoleRelationship(id);
		roleRepository.delete(id);
	}

	@Override
	public List<RoleUserDto> getRoleStatistic() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RoleUserDto> query = cb.createQuery(RoleUserDto.class);
		Root<Role> root = query.from(Role.class);
		Join<Role, User> user = root.join(Role._userList, JoinType.LEFT);
		query.groupBy(root.get(Role._id));
		query.multiselect(root.get(Role._id), root.get(Role._name), root.get(Role._title),
				cb.count(user.get(User._id)));
		return em.createQuery(query).getResultList();
	}

}
