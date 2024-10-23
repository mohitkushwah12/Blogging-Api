package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.dollop.app.entity.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer>
{
//	@Query("UPDATE ToDoList t SET t.completed = true WHERE t.toDoListId = :id AND t.user.id = :userId")
//    void markTodoAsComplete(@Param("id") Integer toDoListId, @Param("userId") Integer userId);
//	
//	@Query("UPDATE ToDoList t SET t.completed = false WHERE t.toDoListId = :id AND t.user.id = :userId")
//    void markTodoAsUncomplete(@Param("id") Integer toDoListId, @Param("userId") Integer userId);
	
	@Query("SELECT t FROM ToDoList t WHERE t.toDoListId = :id AND t.user.id = :userId")
    ToDoList findTodoByIdAndUserId(@Param("id") Integer toDoListId, @Param("userId") Integer userId);
	
	@Modifying
    @Query("UPDATE ToDoList t SET t.completed = false WHERE t.toDoListId = :id")
    void markTodoAsUncomplete(@Param("id") Integer toDoListId);
	
	@Modifying
    @Query("UPDATE ToDoList t SET t.completed = true WHERE t.toDoListId = :id")
    void markTodoAsComplete(@Param("id") Integer toDoListId);
}
