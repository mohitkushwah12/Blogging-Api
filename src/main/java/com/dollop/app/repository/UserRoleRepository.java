package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.UserRoles;

public interface UserRoleRepository extends JpaRepository<UserRoles, Integer>
{

}
