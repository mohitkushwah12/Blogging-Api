package com.dollop.app.restcontroller;

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
import com.dollop.app.entity.Address;
import com.dollop.app.service.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressRestController 
{
	@Autowired
	private IAddressService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createAddress(@Validated @RequestBody Address address) {
		Integer u = service.saveAddress(address);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}

//	// 2. Fetch All Student
//	@GetMapping("/all")
//	public ResponseEntity<List<Address>> getAllAddress() {
//		return ResponseEntity.ok(service.getAllAddress());
//	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable("albumsId") Integer addressId)
	{
		return new ResponseEntity<>(service.updateAddress(address, addressId), HttpStatus.OK);
	}
	
//	@GetMapping("/{addressId}")
//	public ResponseEntity<Address> getOneUser(@PathVariable("albumsId") Integer addressId)
//	{
//		return new ResponseEntity<>(service.getOneAddress(addressId), HttpStatus.OK);
//	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable("addressId") Integer addressId)
	{
		return new ResponseEntity<>(service.deleteAddress(addressId), HttpStatus.OK);
	}
	
	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<Address>> getAllAddress(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "street", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<Address>>(service.getAllAddress(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}
}
