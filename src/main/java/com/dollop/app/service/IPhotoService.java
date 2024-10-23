package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Photos;

public interface IPhotoService 
{
	public List<Photos>getAllPhotos();
	Photos getOnePhoto(Integer photoId);
 	String deletePhoto(Integer photoId) ;
	Integer savePhoto(Photos photo);
	Photos updatePhoto(Photos photo, Integer photoId);
	
	PagebleResponse<Photos>  getAllPhotos(int pageNumber, int pageSize, String sortBy, String sortDir);
}
