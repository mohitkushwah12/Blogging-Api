package com.dollop.app.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Post;
import com.dollop.app.entity.User;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private IUserService service;

	// 1. create one Student
	@PostMapping("/create")
	public ResponseEntity<Integer> createUser(@Validated @RequestBody User user) throws DuplicateEntryException {
		Integer u = service.saveUser(user);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}

//	// 2. Fetch All Student
//	@GetMapping("/all")
//	public ResponseEntity<List<User>> getAllStudents() {
//		return ResponseEntity.ok(service.getAllUsers());
//	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(service.updateUser(user, userId), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getOneUser(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(service.getOneUser(userId), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(service.deleteUser(userId), HttpStatus.OK);
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<User>> searchUser(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(service.searchUser(keyword), HttpStatus.OK);
	}

	@GetMapping("/posts/{keyword}")
	public ResponseEntity<List<Post>> getPostByUsernmae(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(service.getPostsByUser(keyword), HttpStatus.OK);
	}

	@GetMapping("/albums/{keyword}")
	public ResponseEntity<List<Albums>> getAlbumsByUsername(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(service.getAlbumsByUser(keyword), HttpStatus.OK);
	}

//	@GetMapping("/login")
//	public ResponseEntity<Map<String, Object>> loginUser(String userName, String password) {
//		return new ResponseEntity<Map<String, Object>>(service.loginUser(password, userName), HttpStatus.OK);
//	}

	@GetMapping("/checkUsernameAvailability")
	public ResponseEntity<String> checkUsernameAvailability(@RequestParam String userName) {
		String isAvailable = service.checkUserNameAvailability(userName);
		return ResponseEntity.ok(isAvailable);
	}

	@GetMapping("/checkEmailAvailability")
	public ResponseEntity<String> checkEmailAvailability(@RequestParam String userEmail) {
		String availability = service.checkEmailAvailability(userEmail);
		return ResponseEntity.ok(availability);
	}

	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<User>> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "userFirstName", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<User>>(service.getAllUsers(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}

	@PutMapping("/{userId}/activate")
	public ResponseEntity<Void> activateUser(@PathVariable Integer userId) {
		service.activateUser(userId);
		return ResponseEntity.ok().build();
	}

//	 @PutMapping("/{userName}/giveAdmin")
//	    public ResponseEntity<User> giveAdminRoleToUser(@PathVariable("userName") String userName) {
//	        service.giveAdminRoleToUser(userName);
//	        return new ResponseEntity<>(HttpStatus.OK);
//	    }
}
