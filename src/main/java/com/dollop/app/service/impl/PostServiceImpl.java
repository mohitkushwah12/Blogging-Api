package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Post;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.PostRepository;
import com.dollop.app.service.IPostService;

@Service
public class PostServiceImpl implements IPostService
{
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Post> getAllPosts() {
		List<Post> list = postRepo.findAll();
		return list;
	}

	@Override
	public Post getOnePost(Integer postId) {
		return postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post " +postId+" Not exist")
				);
	}

	@Override
	public String deletePost(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(
				() -> new UserNotFoundException("Post Not Found Given Post Id "+ postId));
		post.setActive(false);
		postRepo.save(post);
		return "Post "+postId+" Delete Successfully";
	}

	@Override
	public Integer savePost(Post user) {
		user = postRepo.save(user);
		return user.getPostId();
	}

	@Override
	public Post updatePost(Post post, Integer postId) {
		@SuppressWarnings("unused")
		Post posts = postRepo.findById(postId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception for "+ postId));
		post.setPostName(post.getPostName());
		post.setPostBody(post.getPostBody());
		//post.setCreatedAt(post.getCreatedAt());
		post.setCreatedBy(post.getCreatedBy());
		post.setUpdatedBy(post.getUpdatedBy());
		post.setUser(post.getUser());
		Post updatedPost = postRepo.save(post);
		
		return updatedPost;
	}

	@Override
	public PagebleResponse<Post> getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = postRepo.findAll(pageable);
		PagebleResponse<Post> response = Helper.getPageableResponse(page, Post.class);
		return response;
	}
	
}
