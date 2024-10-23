package com.dollop.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dollop.app.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>
{
	@Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
	List<Comment> findByPostId(Integer postId);
}
