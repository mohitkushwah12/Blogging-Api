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
import com.dollop.app.entity.Tags;
import com.dollop.app.service.ITagService;

@RestController
@RequestMapping("/api/tags")
public class TagRestController 
{
	@Autowired
	private ITagService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createTag(@RequestBody Tags tags) {
		Integer u = service.saveTag(tags);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/{tagsId}")
	public ResponseEntity<Tags> updateTag(@RequestBody Tags tags, @PathVariable("tagsId") Integer tagsId)
	{
		return new ResponseEntity<>(service.updateTag(tags, tagsId), HttpStatus.OK);
	}
	
	@GetMapping("/{tagsId}")
	public ResponseEntity<Tags> getOneTag(@PathVariable("tagsId") Integer tagsId)
	{
		return new ResponseEntity<>(service.getOneTag(tagsId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{tagsId}")
	public ResponseEntity<String> deleteTag(@PathVariable("tagsId") Integer tagsId)
	{
		return new ResponseEntity<>(service.deleteTag(tagsId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Tags>> getAllTags() {
		return ResponseEntity.ok(service.getAllTags());
	}
	
	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<Tags>> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "tagsName", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<Tags>>(service.getAllTags(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}
	
}
