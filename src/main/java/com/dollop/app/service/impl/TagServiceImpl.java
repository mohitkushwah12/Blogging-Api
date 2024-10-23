package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Tags;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.TagRepository;
import com.dollop.app.service.ITagService;


@Service
public class TagServiceImpl implements ITagService
{
	@Autowired
	private TagRepository tagRepo;
	
	@Override
	public Integer saveTag(Tags tags) {
		tags = tagRepo.save(tags);
		return tags.getTagsId();
	}

	@Override
	public Tags updateTag(Tags tags, Integer tagsId) {
		
		@SuppressWarnings("unused")
		Tags tag = tagRepo.findById(tagsId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception For "+ tagsId));
		tags.setTagsName(tags.getTagsName());
		Tags updatedTags = tagRepo.save(tags);
		
		return updatedTags;
	}

	@Override
	public List<Tags> getAllTags() {
		List<Tags> list = tagRepo.findAll();
		return list;
	}

	@Override
	public Tags getOneTag(Integer tagsId) {
		return tagRepo.findById(tagsId).orElseThrow(
				()-> new ResourceNotFoundException("Tag " +tagsId+" Not exist")
				);
	}

	@Override
	public String deleteTag(Integer tagsId) {
	
		Tags tags = tagRepo.findById(tagsId).orElseThrow(
				() -> new UserNotFoundException("Tag Not Found Given Tag Id "+ tagsId));
		tags.setActive(false);
		tagRepo.save(tags);
		return "Tags "+tagsId+" Delete Successfully";
	}

	@Override
	public PagebleResponse<Tags> getAllTags(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Tags> page = tagRepo.findAll(pageable);
		PagebleResponse<Tags> response = Helper.getPageableResponse(page, Tags.class);
		return response;
	}
	
}
