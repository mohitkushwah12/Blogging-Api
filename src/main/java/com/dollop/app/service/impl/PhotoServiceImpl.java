package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Photos;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.PhotoRepository;
import com.dollop.app.service.IPhotoService;

@Service
public class PhotoServiceImpl implements IPhotoService
{
	@Autowired
	private PhotoRepository photoRepo;

	@Override
	public List<Photos> getAllPhotos() {
		List<Photos> list = photoRepo.findAll();
		return list;
	}

	@Override
	public Photos getOnePhoto(Integer photoId) {
		return photoRepo.findById(photoId).orElseThrow(
				()-> new ResourceNotFoundException("Photo " +photoId+" Not exist")
				);
	}

	@Override
	public String deletePhoto(Integer photoId) {
		
		Photos photo = photoRepo.findById(photoId).orElseThrow(
				() -> new UserNotFoundException("Photo Not Found Given Photo Id "+ photoId));
		photo.setActive(false);
		photoRepo.save(photo);
		return "Photo "+photoId+" Delete Successfully";
	}

	@Override
	public Integer savePhoto(Photos photo) {
		photo = photoRepo.save(photo);
		return photo.getPhotoId();
	}

	@Override
	public Photos updatePhoto(Photos photo, Integer photoId) {
		@SuppressWarnings("unused")
		Photos photos = photoRepo.findById(photoId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception for "+ photoId));
		photo.setPhotoName(photo.getPhotoName());
		photo.setPhotoType(photo.getPhotoType());
		photo.setPhotoThumbNail(photo.getPhotoThumbNail());
		//photo.setImagePath(photo.getImagePath());
		//photo.setCreatedAt(photo.getCreatedAt());
		
		Photos updatedUser = photoRepo.save(photo);
		
		return updatedUser;
	}

	@Override
	public PagebleResponse<Photos> getAllPhotos(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Photos> page = photoRepo.findAll(pageable);
		PagebleResponse<Photos> response = Helper.getPageableResponse(page, Photos.class);
		return response;
	}
	
}
