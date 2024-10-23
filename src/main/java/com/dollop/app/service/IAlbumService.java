package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Photos;

public interface IAlbumService
{
	List<Albums> getAllAlbums();
	Albums getOneAlbum(Integer albumsId);
 	String deleteAlbum(Integer albumsId) ;
	Integer saveAlbum(Albums album);
	Albums updateAlbum(Albums album, Integer albumsId);
	
	List<Photos> getAllPhotosForAlbum(Integer albumsId);
	
	PagebleResponse<Albums> getAllAlbums(int pageNumber, int pageSize, String sortBy, String sortDir);
}
