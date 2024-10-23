package com.dollop.app.service;

import com.dollop.app.entity.Roles;

public interface IRoleService 
{
	String deleteRole(Long rolesId) ;
	Integer saveRole(Roles role);
	Roles updateRole(Roles role, Long rolesId);
}
