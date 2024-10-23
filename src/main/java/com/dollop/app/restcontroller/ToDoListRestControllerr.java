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
import com.dollop.app.entity.ToDoList;
import com.dollop.app.service.IToDoService;

@RestController
@RequestMapping("/api/todolist")
public class ToDoListRestControllerr 
{
	@Autowired
	private IToDoService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createToDoList(@RequestBody ToDoList toDoList) {
		Integer u = service.saveToDoList(toDoList);
		// String message = "Student " + u + " Created";
		ResponseEntity<Integer> response = new ResponseEntity<>(u, HttpStatus.CREATED);
		return response;
	}

	// 2. Fetch All Student
	@GetMapping("/all")
	public ResponseEntity<List<ToDoList>> getAllToDoList() {
		return ResponseEntity.ok(service.getAllToDoList());
	}
	
	@PutMapping("/{toDoListId}")
	public ResponseEntity<ToDoList> updateToDoList(@RequestBody ToDoList toDoList, @PathVariable("albumsId") Integer toDoListId)
	{
		return new ResponseEntity<>(service.updateToDoList(toDoList, toDoListId), HttpStatus.OK);
	}
	
	@GetMapping("/{toDoListId}")
	public ResponseEntity<ToDoList> getOneToDoList(@PathVariable("toDoListId") Integer toDoListId)
	{
		return new ResponseEntity<>(service.getOneToDoList(toDoListId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{toDoListId}")
	public ResponseEntity<String> deleteToDoList(@PathVariable("toDoListId") Integer toDoListId)
	{
		return new ResponseEntity<>(service.deleteToDoList(toDoListId), HttpStatus.OK);
	}
	
	@GetMapping("/alldata")
	public ResponseEntity<PagebleResponse<ToDoList>> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "toDoListTitle", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<PagebleResponse<ToDoList>>(service.getAllToDoLists(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}
	
	@PutMapping("/{toDoListId}/complete")
    public ResponseEntity<Void> markTodoAsComplete(@PathVariable Integer toDoListId, @RequestParam Integer userId) {
		service.markTodoAsComplete(toDoListId, userId);
        return ResponseEntity.ok().build();
    }

	@PutMapping("/{toDoListId}/uncomplete")
    public ResponseEntity<Void> markTodoAsUncomplete(@PathVariable Integer toDoListId, @RequestParam Integer userId) {
		service.markTodoAsUncomplete(toDoListId, userId);
        return ResponseEntity.ok().build();
    }
}
