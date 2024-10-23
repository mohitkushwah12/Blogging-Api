package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Tags;

public interface ITagService 
{
	List<Tags> getAllTags();
	Integer saveTag(Tags tags);
	Tags updateTag(Tags tags, Integer tagsId);
	Tags getOneTag(Integer tagsId);
 	String deleteTag(Integer tagsId) ;
 	
 	PagebleResponse<Tags>  getAllTags(int pageNumber, int pageSize, String sortBy, String sortDir);
}
