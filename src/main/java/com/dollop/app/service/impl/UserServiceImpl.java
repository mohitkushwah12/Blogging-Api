package com.dollop.app.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Post;
import com.dollop.app.entity.RoleName;
import com.dollop.app.entity.Roles;
import com.dollop.app.entity.User;
import com.dollop.app.entity.UserRoles;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.UserRepository;
import com.dollop.app.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Integer saveUser(User user) throws DuplicateEntryException 
	{
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

	@Override
	public List<User> getAllUsers() 
	{
		List<User> list=userRepo.findByIsActiveTrue();
		return list;
	}
	
	@Override
	public User getOneUser(Integer userId) {
		User user= userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User Not Found By Given Id "+ userId));
		if(user.isActive())
		{
			return user;
		}
		else
		{
			throw new ResourceNotFoundException("User not found for id "+ userId);
		}
	}


	@Override
	public User updateUser(User user, Integer userId) {
		
		@SuppressWarnings("unused")
		User userr = userRepo.findById(userId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception for "+ userId));
		user.setUserFirstName(user.getUserFirstName());
		user.setUserLastName(user.getUserLastName());
		user.setUserName(user.getUserName());
		user.setUserPassword(user.getUserPassword());
		user.setUserEmail(user.getUserEmail());
		user.setUserPhone(user.getUserPhone());
		user.setUserWebsite(user.getUserWebsite());
		User updatedUser = userRepo.save(user);
		
		return updatedUser;
	}

	@Override
	public String deleteUser(Integer userId) 
	{
		User user = userRepo.findById(userId).orElseThrow(
				() -> new UserNotFoundException("User Not Found Given User Id "+ userId));
		user.setActive(false);
		userRepo.save(user);
		return "User "+userId+" Delete Successfully";
	}

	@Override
	public List<User> searchUser(String keyword) {
		
		List<User> u = userRepo.findByuserNameContaining(keyword);
		if (u != null)
		{
			List<User> userr = u.stream()
					.map((userd)->mapper.map(userd, User.class))
					.collect(Collectors.toList());
			
			return userr;
		}
		else
		{
			return u;
		}
	}

	@Override
	public List<Post> getPostsByUser(String keyword) 
	{
		 User u = userRepo.findByuserNameContainings(keyword);
	        if (u == null) {
	            throw new ResourceNotFoundException("User not found with username: " + keyword);
	        }
	        return u.getUserPost();
	}

	@Override
	public List<Albums> getAlbumsByUser(String keyword) {
		 
		User user = userRepo.findByuserNameContainings(keyword);
	        if (user == null) {
	            throw new ResourceNotFoundException("User not found with username: " + keyword);
	        }
	        return user.getUserAlbums();
	}

	@Override
	public Map<String, Object> loginUser(String password, String userName) {
		
		Optional<User> user = userRepo.findByUserNameAndUserPassword(userName, password);
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
	public String checkUserNameAvailability(String userName) {
		return userRepo.checkUserNameAvailability(userName);
	}

	@Override
	public String checkEmailAvailability(String userEmail) {
		return userRepo.checkEmailAvailability(userEmail);
	}

	@Override
	public PagebleResponse<User> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<User> page = userRepo.findAll(pageable);
		PagebleResponse<User> response = Helper.getPageableResponse(page, User.class);
		return response;
	}

	@Override
	@Transactional
	public void activateUser(Integer userId) {
		// TODO Auto-generated method stub
		userRepo.activateUserById(userId);
	}

//	@Override
////	@Transactional
//	public User giveAdminRoleToUser(String userName) {
//		return userRepo.giveAdminRoleToUser(userName);
//	}
}
