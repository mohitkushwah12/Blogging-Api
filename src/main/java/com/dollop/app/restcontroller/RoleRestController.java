package com.dollop.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.entity.Roles;
import com.dollop.app.service.IRoleService;

@RestController
@RequestMapping("/api/role")
public class RoleRestController 
{
	@Autowired
	private IRoleService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> saveRole(@RequestBody Roles role) 
	{
		Integer u = service.saveRole(role);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}
	
	@DeleteMapping("/{rolesId}")
	public ResponseEntity<String> deleteRole(@PathVariable("rolesId") Long rolesId)
	{
		return new ResponseEntity<>(service.deleteRole(rolesId), HttpStatus.OK);
	}
	
}
