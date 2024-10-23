package com.dollop.app.restcontroller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.entity.User;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.service.IUserService;

@RestController
@RequestMapping("/api/auth/signin")
public class AuthResponseRestController 
{
	@Autowired
	private IUserService service;
	
	@GetMapping("/Sign In")
	public ResponseEntity<Map<String, Object>> loginUser(String userName, String userPassword) {
		return new ResponseEntity<Map<String, Object>>(service.loginUser(userPassword, userName), HttpStatus.OK);
	}
	
	@PostMapping("/Sign Up")
	public ResponseEntity<Integer> createUser(@Validated @RequestBody User user) throws DuplicateEntryException {
		Integer u = service.saveUser(user);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}
}
