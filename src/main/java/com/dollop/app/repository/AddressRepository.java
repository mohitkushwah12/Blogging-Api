package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>
{

}
