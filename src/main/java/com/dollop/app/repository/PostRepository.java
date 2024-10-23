package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dollop.app.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>
{

}
