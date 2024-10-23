package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.PostTag;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.PostTagRepository;
import com.dollop.app.service.IPostTagService;

@Service
public class PostTagServiceImpl implements IPostTagService
{
	@Autowired
	private PostTagRepository postTagRepo;
	
	@Override
	public List<PostTag> getAllPostTags() {
		List<PostTag> list = postTagRepo.findAll();
		return list;
	}

	@Override
	public PostTag getOnePostTag(Integer postTagId) {
		return postTagRepo.findById(postTagId).orElseThrow(
				()-> new ResourceNotFoundException("PostTag " +postTagId+" Not exist")
				);
	}

	@Override
	public String deletePostTag(Integer postTagId) {
		
		PostTag postTag = postTagRepo.findById(postTagId).orElseThrow(
				() -> new UserNotFoundException("PostTag Not Found Given PostTag Id "+ postTagId));
		postTag.setActive(false);
		postTagRepo.save(postTag);
		return "PostTag "+postTagId+" Delete Successfully";
	}

	@Override
	public Integer savePostTag(PostTag postTag) {
		postTag = postTagRepo.save(postTag);
		return postTag.getPostTagId();
	}

	@Override
	public PostTag updatePostTag(PostTag postTag, Integer postTagId) {

		@SuppressWarnings("unused")
		PostTag posts = postTagRepo.findById(postTagId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception For "+ postTagId));
		postTag.setPost(postTag.getPost());
		postTag.setTag(postTag.getTag());
		PostTag updatedPostTag = postTagRepo.save(postTag);
		
		return updatedPostTag;	
	}

	@Override
	public PagebleResponse<PostTag> getAllPostTags(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<PostTag> page = postTagRepo.findAll(pageable);
		PagebleResponse<PostTag> response = Helper.getPageableResponse(page, PostTag.class);
		return response;
	}
	
}
