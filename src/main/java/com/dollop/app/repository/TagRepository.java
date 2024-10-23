package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.Tags;

public interface TagRepository extends JpaRepository<Tags, Integer>
{

}
