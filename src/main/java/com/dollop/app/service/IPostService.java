package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Post;

public interface IPostService 
{
	List<Post> getAllPosts();
	Post getOnePost(Integer postId);
 	String deletePost(Integer postId) ;
	Integer savePost(Post user);
	Post updatePost(Post user, Integer postId);
	
	PagebleResponse<Post>  getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
}
