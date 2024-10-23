package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.entity.Roles;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repository.RoleRepository;
import com.dollop.app.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService
{
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public String deleteRole(Long rolesId) {
		
		@SuppressWarnings("unused")
		Roles role = roleRepo.findById(rolesId).orElseThrow(
				() -> new ResourceNotFoundException("Role Not Found Given Role Id "+ rolesId));
		roleRepo.deleteById(rolesId);
		return "Role "+rolesId+" Delete Successfully";
	}

	@Override
	public Integer saveRole(Roles role) {
		role = roleRepo.save(role);
		return role.getRolesId();
	}

	@Override
	public Roles updateRole(Roles role, Long rolesId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Roles updateRole(Roles user, Long rolesId) {
//		
//		@SuppressWarnings("unused")
//		Optional<Roles> roles = roleRepo.findById(rolesId);
//		roles.setRolesName(roles.getRolesName());
//		
//		Post updatedPost = postRepo.save(post);
//		
//		return updatedPost;
//	}

}
