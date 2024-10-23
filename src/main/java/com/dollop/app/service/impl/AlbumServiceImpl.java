package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Photos;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.AlbumRepository;
import com.dollop.app.repository.PhotoRepository;
import com.dollop.app.service.IAlbumService;

@Service
public class AlbumServiceImpl implements IAlbumService
{
	@Autowired
	private AlbumRepository albumRepo;
	
	@Autowired
	private PhotoRepository photoRepo;

	@Override
	public List<Albums> getAllAlbums() {
		List<Albums> list = albumRepo.findAll();
		return list;
	}

	@Override
	public Albums getOneAlbum(Integer albumsId) {
		return albumRepo.findById(albumsId).orElseThrow(
				()-> new ResourceNotFoundException("Album " +albumsId+" Not exist")
				);
	}

	@Override
	public String deleteAlbum(Integer albumsId) {
		@SuppressWarnings("unused")
		Albums user = albumRepo.findById(albumsId).orElseThrow(
				() -> new UserNotFoundException("Album Not Found Given Album Id "+ albumsId));
		albumRepo.deleteById(albumsId);
		return "Album "+albumsId+" Delete Successfully";
	}

	@Override
	public Integer saveAlbum(Albums album) {
		album = albumRepo.save(album);
		return album.getAlbumsId();
	}

	@Override
	public Albums updateAlbum(Albums album, Integer albumsId) {
		
		@SuppressWarnings("unused")
		Albums albums = albumRepo.findById(albumsId).orElseThrow(
				() -> new EntityNotFoundException("Entity not found Exception for "+ albumsId));
		album.setAlbumsName(album.getAlbumsName());
		Albums updatedAlbum = albumRepo.save(album);
		
		return updatedAlbum;
	}

	@Override
	public List<Photos> getAllPhotosForAlbum(Integer albumsId) {
		return photoRepo.findByAlbumId(albumsId);
	}

	@Override
	public PagebleResponse<Albums> getAllAlbums(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Albums> page = albumRepo.findAll(pageable);
		PagebleResponse<Albums> response = Helper.getPageableResponse(page, Albums.class);
		return response;
	}
	
}
