package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.PostTag;

public interface IPostTagService 
{
	List<PostTag> getAllPostTags();
	PostTag getOnePostTag(Integer postTagId);
 	String deletePostTag(Integer postTagId) ;
	Integer savePostTag(PostTag postTag);
	PostTag updatePostTag(PostTag postTag, Integer postTagId);
	
	PagebleResponse<PostTag>  getAllPostTags(int pageNumber, int pageSize, String sortBy, String sortDir);
}
