package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.PostTag;

public interface PostTagRepository extends JpaRepository<PostTag, Integer>
{

}
