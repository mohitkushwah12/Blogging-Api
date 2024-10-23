package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.ToDoList;

public interface IToDoService 
{
	List<ToDoList> getAllToDoList();
	ToDoList getOneToDoList(Integer toDoListId);
 	String deleteToDoList(Integer toDoListId) ;
	Integer saveToDoList(ToDoList toDoList);
	ToDoList updateToDoList(ToDoList toDoList, Integer toDoListId);
	
	PagebleResponse<ToDoList>  getAllToDoLists(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	void markTodoAsUncomplete(Integer toDoListId, Integer userId);
	
	void markTodoAsComplete(Integer toDoListId, Integer userId);
//	
//	void markTodoAsUncomplete(Integer toDoListId, Integer userId);
}
