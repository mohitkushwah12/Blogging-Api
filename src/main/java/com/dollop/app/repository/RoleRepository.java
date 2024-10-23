package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long>
{

}
