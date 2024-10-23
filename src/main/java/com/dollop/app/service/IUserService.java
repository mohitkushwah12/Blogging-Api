package com.dollop.app.service;

import java.util.List;
import java.util.Map;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Post;
import com.dollop.app.entity.User;
import com.dollop.app.exception.DuplicateEntryException;

public interface IUserService 
{
	public List<User>getAllUsers();
	
	User getOneUser(Integer userId);
 	
	String deleteUser(Integer userId) ;
	
	Integer saveUser(User user) throws DuplicateEntryException;
	
	User updateUser(User user, Integer userId);
	
	List<User> searchUser(String keyword);
	
	List<Post> getPostsByUser(String keyword); 
	
	List<Albums> getAlbumsByUser(String keyword);
	
	Map<String, Object> loginUser(String password, String userName);
	
	String checkUserNameAvailability(String userName);
	
	String checkEmailAvailability(String userEmail);
	
//	User giveAdminRoleToUser(String userName);
	
	void activateUser(Integer userId);
	
	PagebleResponse<User> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);
}
