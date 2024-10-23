package com.dollop.app.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userRole")
public class UserRoleRestController 
{
//	@Autowired
//	private IUserRolesService service;
//	
//	@PostMapping("/create")
//	public ResponseEntity<Integer> createUserRole(@RequestBody UserRoles userRole) 
//	{
//		Integer u = service.saveUserRole(userRole);
//		// String message = "Student " + u + " Created";
//		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
//		return response;
//	}
//	
//	@DeleteMapping("/{userRoleId}")
//	public ResponseEntity<String> deleteUserRole(@PathVariable("userRoleId") Integer userRoleId)
//	{
//		return new ResponseEntity<>(service.deleteUserRole(userRoleId), HttpStatus.OK);
//	}
//	
//	@PutMapping("/{userRoleId}")
//	public ResponseEntity<UserRoles> updateUserRole(@RequestBody UserRoles userRole, @PathVariable("userRoleId") Integer userRoleId)
//	{
//		return new ResponseEntity<>(service.updateUserRole(userRole, userRoleId), HttpStatus.OK);
//	}
}
