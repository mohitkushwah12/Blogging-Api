package com.dollop.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dollop.app.dtos.PagebleResponse;
import com.dollop.app.entity.Comment;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.helper.Helper;
import com.dollop.app.repository.CommentRepository;
import com.dollop.app.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService
{
	@Autowired
	private CommentRepository comRepo;

	@Override
	public List<Comment> getAllComments() {
		List<Comment> list = comRepo.findAll();
		return list;
	}

	@Override
	public Comment getOneComment(Integer commentId) {
		return comRepo.findById(commentId).orElseThrow(
				()-> new ResourceNotFoundException("Comment " +commentId+" Not exist")
				);
	}

	@Override
	public String deleteComment(Integer commentId) {
		@SuppressWarnings("unused")
		Comment c = comRepo.findById(commentId).orElseThrow(
				() -> new UserNotFoundException("Comment Not Found Given Comment Id "+ commentId));
		comRepo.deleteById(commentId);
		return "Comment "+commentId+" Delete Successfully";
	}

	@Override
	public Integer saveComment(Comment comment) {
		comment = comRepo.save(comment);
		return comment.getCommentId();
	}

	@Override
	public Comment updateComment(Comment comment, Integer commentId) {
		@SuppressWarnings("unused")
		Comment com = comRepo.findById(commentId).orElseThrow(
				() -> new EntityNotFoundException("Entity not found exception for "+ commentId));
		comment.setCommentBody(comment.getCommentBody());
		Comment updatedAlbum = comRepo.save(comment);
		
		return updatedAlbum;
	}

	@Override
	public List<Comment> getAllCommentsForPost(Integer postId) {
		return comRepo.findByPostId(postId);
	}

	@Override
	public PagebleResponse<Comment> getAllComments(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Comment> page = comRepo.findAll(pageable);
		PagebleResponse<Comment> response = Helper.getPageableResponse(page, Comment.class);
		return response;
	}
	
}
