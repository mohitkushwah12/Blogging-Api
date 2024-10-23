package com.dollop.app.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Photos;
import com.dollop.app.service.IAlbumService;

@RestController
@RequestMapping("/api/album")
public class AlbumRestController 
{
	@Autowired
	private IAlbumService service;
	
			// 1. create one Student
			@PostMapping("/create")
			public ResponseEntity<Integer> createAlbum(@Validated @RequestBody Albums album) {
				Integer u = service.saveAlbum(album);
				// String message = "Student " + u + " Created";
				ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
				return response;
			}

			// 2. Fetch All Student
			@GetMapping("/all")
			public ResponseEntity<List<Albums>> getAllAlbums() {
				return ResponseEntity.ok(service.getAllAlbums());
			}
			
			@PutMapping("/{albumsId}")
			public ResponseEntity<Albums> updateAlbum(@RequestBody Albums album, @PathVariable("albumsId") Integer albumsId)
			{
				return new ResponseEntity<>(service.updateAlbum(album, albumsId), HttpStatus.OK);
			}
			
			@GetMapping("/{albumsId}")
			public ResponseEntity<Albums> getOneAlbum(@PathVariable("albumsId") Integer albumsId)
			{
				return new ResponseEntity<>(service.getOneAlbum(albumsId), HttpStatus.OK);
			}
			
			@DeleteMapping("/{albumsId}")
			public ResponseEntity<String> deleteAlbum(@PathVariable("albumsId") Integer albumsId)
			{
				return new ResponseEntity<>(service.deleteAlbum(albumsId), HttpStatus.OK);
			}
			
			@GetMapping("/Photos/{albumsId}")
			public ResponseEntity<List<Photos>> getCommnetByPostId(@PathVariable("albumsId") Integer albumsId)
			{
				return new ResponseEntity<List<Photos>>(service.getAllPhotosForAlbum(albumsId), HttpStatus.OK);
			}
			
			@GetMapping("/alldata")
			public ResponseEntity<PagebleResponse<Albums>> getAllUsers(
					@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
					@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
					@RequestParam(value = "sortBy", defaultValue = "albumsName", required = false) String sortBy,
					@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
				return new ResponseEntity<PagebleResponse<Albums>>(service.getAllAlbums(pageNumber, pageSize, sortBy, sortDir),
						HttpStatus.OK);
			}
}
