package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Address;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.AddressRepository;
import com.dollop.app.service.IAddressService;

@Service
public class AddressImpl implements IAddressService
{
	@Autowired
	private AddressRepository addRepo;
	
	@Override
	public List<Address> getAllAddress() {
		List<Address> list = addRepo.findAll();
		return list;
	}

	@Override
	public Address getOneAddress(Integer addressId) {
		return addRepo.findById(addressId).orElseThrow(
				()-> new ResourceNotFoundException("Address " +addressId+" Not exist")
				);
	}

	@Override
	public String deleteAddress(Integer addressId) {
		@SuppressWarnings("unused")
		Address address = addRepo.findById(addressId).orElseThrow(
				() -> new UserNotFoundException("Address Not Found Given Address Id "+ addressId));
		addRepo.deleteById(addressId);
		return "Address "+addressId+" Delete Successfully";
	}

	@Override
	public Integer saveAddress(Address address) {
		address = addRepo.save(address);
		return address.getAddressId();
	}

	@Override
	public Address updateAddress(Address address, Integer addressId) {
		@SuppressWarnings("unused")
		Address addres = addRepo.findById(addressId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception For "+ addressId));
		address.setStreet(address.getStreet());
		address.setSuite(address.getSuite());
		address.setCity(address.getCity());
		Address updatedAddress = addRepo.save(address);
		
		return updatedAddress;
	}

	@Override
	public PagebleResponse<Address> getAllAddress(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Address> page = addRepo.findAll(pageable);
		PagebleResponse<Address> response = Helper.getPageableResponse(page, Address.class);
		return response;
	}

}
