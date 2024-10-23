package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Address;

public interface IAddressService
{
	List<Address> getAllAddress();
	Address getOneAddress(Integer addressId);
 	String deleteAddress(Integer addressId) ;
	Integer saveAddress(Address address);
	Address updateAddress(Address address, Integer addressId);
	
	PagebleResponse<Address> getAllAddress(int pageNumber, int pageSize, String sortBy, String sortDir);
}
