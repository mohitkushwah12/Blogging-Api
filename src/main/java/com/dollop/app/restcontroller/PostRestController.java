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
import com.dollop.app.entity.Post;
import com.dollop.app.service.IPostService;


@RestController
@RequestMapping("/api/post")
public class PostRestController 
{
	@Autowired
	private IPostService service;
	
	// 1. create one Student
		@PostMapping("/create")
		public ResponseEntity<Integer> createPost(@RequestBody Post post) {
			Integer u = service.savePost(post);
			// String message = "Student " + u + " Created";
			ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
			return response;
		}

		// 2. Fetch All Student
		@GetMapping("/all")
		public ResponseEntity<List<Post>> getAllPosts() {
			return ResponseEntity.ok(service.getAllPosts());
		}
		
		@PutMapping("/{postId}")
		public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("postId") Integer postId)
		{
			return new ResponseEntity<>(service.updatePost(post, postId), HttpStatus.OK);
		}
		
		@GetMapping("/{postId}")
		public ResponseEntity<Post> getOnePost(@PathVariable("postId") Integer postId)
		{
			return new ResponseEntity<>(service.getOnePost(postId), HttpStatus.OK);
		}
		
		@DeleteMapping("/{postId}")
		public ResponseEntity<String> deletePost(@PathVariable("postId") Integer postId)
		{
			return new ResponseEntity<>(service.deletePost(postId), HttpStatus.OK);
		}
		
		@GetMapping("/alldata")
		public ResponseEntity<PagebleResponse<Post>> getAllUsers(
				@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
				@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
				@RequestParam(value = "sortBy", defaultValue = "postName", required = false) String sortBy,
				@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
			return new ResponseEntity<PagebleResponse<Post>>(service.getAllPosts(pageNumber, pageSize, sortBy, sortDir),
					HttpStatus.OK);
		}
}
