package com.dollop.app.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.AuthResponse;
import com.dollop.app.dtos.SignInRequest;
import com.dollop.app.dtos.SignUpRequest;
import com.dollop.app.dtos.UserDto;
import com.dollop.app.entity.RoleName;
import com.dollop.app.entity.Roles;
import com.dollop.app.entity.User;
import com.dollop.app.entity.UserRoles;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repository.UserRepository;
import com.dollop.app.service.IAuthResponseService;

@Service
public class AuthResponseServiceImpl implements IAuthResponseService
{	
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDto signUp(SignUpRequest signUpRequest) {
		return null;
	}

	@Override
	public AuthResponse signIn(SignInRequest signInRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> loginUser(String userPassword, String userName) {
		
		Optional<User> user = userRepo.findByUserNameAndUserPassword(userName, userPassword);
		Map<String, Object> map = new HashMap<>();
		if(user.isPresent())
		{
			map.put("message", "Login Successfully");
			map.put("user", user);
			return map;
		}
		else
		{
			throw new ResourceNotFoundException("User Not found");
		}
	}

	@Override
	public Integer signUp(User user) throws DuplicateEntryException {
		
		if (userRepo.existsByUsername(user.getUserName())) {
            throw new DuplicateEntryException("Username already exists");
        }
        if (userRepo.existsByEmail(user.getUserEmail())) {
            throw new DuplicateEntryException("Email already exists");
        }
        
        //user.setUserRoles("ROLE_USER"); // Set user role to "USER"
        UserRoles  userRole = new UserRoles();
        userRole.setUser(user);
        Roles role = new Roles();
        role.setRolesId(101);
        role.setRolesName(RoleName.ROLE_USER);
        userRole.setRoles(role);
        Set<UserRoles> userroles = new HashSet<>();
        userroles.add(userRole);
        user.setUserRoles(userroles);
        user = userRepo.save(user);
        return user.getUserId();
	}

}
