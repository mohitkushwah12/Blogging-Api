package com.dollop.app.restcontroller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dollop.app.dtos.ImageResponse;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Photos;
import com.dollop.app.service.IFileService;
import com.dollop.app.service.IPhotoService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/photo")
public class PhotoRestController {
	
	@Autowired
	private IPhotoService service;

	@Autowired
	private IFileService fileService;

	@Value("${user.profile.image.path}")
	private String imageUploadPath;

	// 1. create one Student
	@PostMapping("/create")
	public ResponseEntity<Integer> createPhoto(@RequestBody Photos photo) {
		Integer u = service.savePhoto(photo);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}

	// 2. Fetch All Student
	@GetMapping("/all")
	public ResponseEntity<List<Photos>> getAllPhotos() {
		return ResponseEntity.ok(service.getAllPhotos());
	}

	@PutMapping("/{photoId}")
	public ResponseEntity<Photos> updatePhoto(@RequestBody Photos photo, @PathVariable("postId") Integer photoId) {
		return new ResponseEntity<>(service.updatePhoto(photo, photoId), HttpStatus.OK);
	}

	@GetMapping("/{photoId}")
	public ResponseEntity<Photos> getOnePhoto(@PathVariable("photoId") Integer photoId) {
		return new ResponseEntity<>(service.getOnePhoto(photoId), HttpStatus.OK);
	}

	@DeleteMapping("/{photoId}")
	public ResponseEntity<String> deletePhoto(@PathVariable("photoId") Integer photoId) {
		return new ResponseEntity<>(service.deletePhoto(photoId), HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@PostMapping("/image/{photoId}")
	public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image,
			@PathVariable("photoId") Integer photoId) throws IOException {
		String imageName = fileService.uploadFile(image, imageUploadPath);
		Photos user = service.getOnePhoto(photoId);
		user.setImagePath(imageName);
		Photos userDto = service.updatePhoto(user, photoId);
		ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).message("image Is Uploaded !")
				.success(true).status(HttpStatus.CREATED).build();
		return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
	}

	@GetMapping("/image/{photoId}")
	public void serveUserImage(@PathVariable Integer photoId, HttpServletResponse response) throws IOException {
		Photos user = service.getOnePhoto(photoId);
		InputStream resource = fileService.getResource(imageUploadPath, user.getImagePath());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<Photos>> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "photoName", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<Photos>>(service.getAllPhotos(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}
}
