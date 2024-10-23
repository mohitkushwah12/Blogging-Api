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
import com.dollop.app.entity.Comment;
import com.dollop.app.service.ICommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {
	
	@Autowired
	private ICommentService service;

	// 1. create one Student
	@PostMapping("/create")
	public ResponseEntity<Integer> createComment(@RequestBody Comment comment) {
		Integer u = service.saveComment(comment);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}

	// 2. Fetch All Student
	@GetMapping("/all/comments")
	public ResponseEntity<List<Comment>> getAllComments() {
		return ResponseEntity.ok(service.getAllComments());
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateComments(@RequestBody Comment comment, @PathVariable("commentId") Integer commentId) {
		return new ResponseEntity<>(service.updateComment(comment, commentId), HttpStatus.OK);
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<Comment> getOneComment(@PathVariable("commentId") Integer commentId) {
		return new ResponseEntity<>(service.getOneComment(commentId), HttpStatus.OK);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentId") Integer commentId) {
		return new ResponseEntity<>(service.deleteComment(commentId), HttpStatus.OK);
	}
	
	@GetMapping("/comment/{postId}")
	public ResponseEntity<List<Comment>> getCommnetByPostId(@PathVariable("postId") Integer postId)
	{
		return new ResponseEntity<List<Comment>>(service.getAllCommentsForPost(postId), HttpStatus.OK);
	}
	
	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<Comment>> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "commentBody", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<Comment>>(service.getAllComments(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}
}
