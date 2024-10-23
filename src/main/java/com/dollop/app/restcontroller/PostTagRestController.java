package com.dollop.app.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.dollop.app.entity.PostTag;
import com.dollop.app.service.IPostTagService;

@RestController
@RequestMapping("/api/posttag")
public class PostTagRestController 
{
	@Autowired
	private IPostTagService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createPostTag(@RequestBody PostTag postTag) {
		Integer u = service.savePostTag(postTag);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}

	// 2. Fetch All Student
	@GetMapping("/all")
	public ResponseEntity<List<PostTag>> getAllPostTags() {
		return ResponseEntity.ok(service.getAllPostTags());
	}
	
	@PutMapping("/{postTagId}")
	public ResponseEntity<PostTag> updatePostTag(@RequestBody PostTag postTag, @PathVariable("postId") Integer postTagId)
	{
		return new ResponseEntity<>(service.updatePostTag(postTag, postTagId), HttpStatus.OK);
	}
	
	@GetMapping("/{postTagId}")
	public ResponseEntity<PostTag> getOnePostTag(@PathVariable("postTagId") Integer postTagId)
	{
		return new ResponseEntity<>(service.getOnePostTag(postTagId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{postTagId}")
	public ResponseEntity<String> deletePostTag(@PathVariable("postTagId") Integer postId)
	{
		return new ResponseEntity<>(service.deletePostTag(postId), HttpStatus.OK);
	}
	
	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<PostTag>> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "post", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<PostTag>>(service.getAllPostTags(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}
}
