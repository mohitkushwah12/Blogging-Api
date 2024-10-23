package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.ToDoList;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.ToDoListRepository;
import com.dollop.app.service.IToDoService;

@Service
public class ToDoServiceImpl implements IToDoService
{
	@Autowired
	private ToDoListRepository toDoListRepo;
	
	@Override
	public List<ToDoList> getAllToDoList() {
		List<ToDoList> list = toDoListRepo.findAll();
		return list;
	}

	@Override
	public ToDoList getOneToDoList(Integer toDoListId) {
		return toDoListRepo.findById(toDoListId).orElseThrow(
				()-> new ResourceNotFoundException("To Do List " +toDoListId+" Not exist")
				);
	}

	@Override
	public String deleteToDoList(Integer toDoListId) {
		
		ToDoList toDoList = toDoListRepo.findById(toDoListId).orElseThrow(
				() -> new UserNotFoundException("To Do List Not Found Given ToDoList Id "+ toDoListId));
		toDoList.setActive(false);
		toDoListRepo.save(toDoList);
		return "To Do List "+toDoListId+" Delete Successfully";
	}

	@Override
	public Integer saveToDoList(ToDoList toDoList) {
		toDoList = toDoListRepo.save(toDoList);
		return toDoList.getToDoListId();
	}

	@Override
	public ToDoList updateToDoList(ToDoList toDoList, Integer toDoListId) {
		@SuppressWarnings("unused")
		ToDoList todolist = toDoListRepo.findById(toDoListId).orElseThrow(
				() -> new EntityNotFoundException("Entity Not Found Exception for "+ toDoListId));
		toDoList.setToDoListTitle(toDoList.getToDoListTitle());
		//toDoList.setCreatedAt(toDoList.getCreatedAt());
		ToDoList updatedtoDoList = toDoListRepo.save(toDoList);
		
		return updatedtoDoList;
	}

	@Override
	public PagebleResponse<ToDoList> getAllToDoLists(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<ToDoList> page = toDoListRepo.findAll(pageable);
		PagebleResponse<ToDoList> response = Helper.getPageableResponse(page, ToDoList.class);
		return response;
	}

	
	@Override
	@Transactional
	public void markTodoAsUncomplete(Integer toDoListId, Integer userId) {
		// TODO Auto-generated method stub
		ToDoList todo = toDoListRepo.findTodoByIdAndUserId(toDoListId, userId);
        if (todo != null) {
        	toDoListRepo.markTodoAsUncomplete(toDoListId);
        } else {
            // Handle case where todo with id and userId is not found
        	new ResourceNotFoundException("TO Do List Not found this todolist Id : "+ toDoListId);
        }
	}

	@Override
	@Transactional
	public void markTodoAsComplete(Integer toDoListId, Integer userId) {
		ToDoList todo = toDoListRepo.findTodoByIdAndUserId(toDoListId, userId);
        if (todo != null) {
        	toDoListRepo.markTodoAsComplete(toDoListId);
        } else {
            // Handle case where todo with id and userId is not found
        	new ResourceNotFoundException("TO Do List Not found this todolist Id : "+ toDoListId);
        }
	}

}
