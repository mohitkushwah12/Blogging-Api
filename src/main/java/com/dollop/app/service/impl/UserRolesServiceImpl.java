package com.dollop.app.service.impl;


import org.springframework.stereotype.Service;
import com.dollop.app.service.IUserRolesService;

@Service
public class UserRolesServiceImpl implements IUserRolesService
{
//	@Autowired
//	private UserRoleRepository userRoleRepo;
//
//	@Override
//	public String deleteUserRole(Integer userRoleId) {
//		
//		@SuppressWarnings("unused")
//		UserRoles role = userRoleRepo.findById(userRoleId).orElseThrow(
//				() -> new UserNotFoundException("User Role Not Found Given UserRole Id "+ userRoleId));
//		userRoleRepo.deleteById(userRoleId);
//		return "User Role"+userRoleId+" Delete Successfully";
//	}
//
//	@Override
//	public Integer saveUserRole(UserRoles userRole) {
//		userRole = userRoleRepo.save(userRole);
//		return userRole.getUserRoleId();
//	}
//
//	@Override
//	public UserRoles updateUserRole(UserRoles userRole, Integer userRoleId) {
//		
//		@SuppressWarnings("unused")
//		UserRoles roles = userRoleRepo.findById(userRoleId).orElseThrow(
//				()-> new EntityNotFoundException("Entity Not Found Exception for "+ userRoleId));
//		UserRoles updatedUserRole = userRoleRepo.save(userRole);
//		
//		return updatedUserRole;
//	}
	
}
