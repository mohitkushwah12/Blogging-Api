package com.dollop.app.service;

import java.util.List;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Comment;

public interface ICommentService
{
	List<Comment> getAllComments();
	Comment getOneComment(Integer commentId);
 	String deleteComment(Integer commentId) ;
	Integer saveComment(Comment comment);
	Comment updateComment(Comment comment, Integer commentId);
	
	List<Comment> getAllCommentsForPost(Integer postId);
	
	PagebleResponse<Comment> getAllComments(int pageNumber, int pageSize, String sortBy, String sortDir);
}
