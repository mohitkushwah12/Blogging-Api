package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.Albums;


public interface AlbumRepository extends JpaRepository<Albums, Integer>
{
	
}
