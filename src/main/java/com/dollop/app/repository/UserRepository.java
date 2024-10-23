package com.dollop.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.dollop.app.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>
{
	@Query("select u from User u where u.isActive = true AND u.userName = :username")
	List<User> findByuserNameContaining(@Param("username") String keyword);
	
	@Query("select u from User u where u.isActive = true")
	List<User> findByIsActiveTrue();

	@Query("select u from User u where u.isActive = true AND u.userName = :username")
	User findByuserNameContainings(@Param("username") String keyword);

	Optional<User> findByUserNameAndUserPassword(String userName, String password);
	
//	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = :username")
//	boolean isUsernameAvailable(@Param("username") String userName);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'Email is not available' ELSE 'Email is available' END FROM User u WHERE u.userEmail = :email")
    String checkEmailAvailability(@Param("email") String userEmail);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'Username is not available' ELSE 'Username is available' END FROM User u WHERE u.userName = :username")
	String checkUserNameAvailability(@Param("username") String userName);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = :username")
	boolean existsByUsername(@Param("username") String userName);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userEmail = :email")
	boolean existsByEmail(@Param("email") String userEmail);
	
	@Modifying
    @Query("UPDATE User u SET u.isActive = true WHERE u.userId = :userId")
    void activateUserById(@Param("userId") Integer userId);
	

//	@Modifying
//    @Query("UPDATE User u SET u.userRoles = 'ROLE_ADMIN' WHERE u.userName = :username")
//	User giveAdminRoleToUser(@Param("username") String userName);
	
//	@Query("select u.albums from User u where u.isActive = true AND u.userName = :username")
//	User findByuserNameContainingss(@Param("username") String keyword);
	
//	@Modifying
//    @Transactional
//    @Query("UPDATE user u SET u.deleted = true WHERE u.id = : userId")
   // String softDeleteUser(Integer userId);

	//User findById(String userId);

	//User findByUsername(String username);

	//void deleteByUsername(String username);

	//Object findByEmail(String email);

	//List<User> getAllTrue();

}
