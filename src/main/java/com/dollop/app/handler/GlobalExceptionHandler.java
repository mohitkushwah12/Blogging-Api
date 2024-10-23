package com.dollop.app.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.dollop.app.bean.ApiResponseMessage;
import com.dollop.app.exception.BadApirequestException;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.EntityNotFoundException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException ex)
	{
		logger.info("Resource Not Found Exception Handler Is Line");
		ApiResponseMessage response = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.status(HttpStatus.NOT_FOUND)
				.success(true).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		Map<String, Object> response = new HashMap<>();
		allErrors.stream().forEach(objectError-> {
			String message = objectError.getDefaultMessage();
			String field = ((FieldError) objectError).getField();
			response.put(field, message);
		});
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadApirequestException.class)
	public ResponseEntity<ApiResponseMessage> badApirequestException(BadApirequestException ex)
	{
		logger.info("Bad Api Exception Handler Is Line");
		ApiResponseMessage response = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST)
				.success(true).build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> entityNotFoundException(EntityNotFoundException ex)
	{
		logger.info("Entity Not Found Exception Handler Is Line");
		ApiResponseMessage response = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.success(true).build();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> userNotFoundException(UserNotFoundException ex)
	{
		logger.info("User Not Found Exception Handler Is Line");
		ApiResponseMessage response = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.status(HttpStatus.NOT_FOUND)
				.success(true).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ApiResponseMessage> duplicateEntryException(DuplicateEntryException ex)
	{
		logger.info("User with the same username or email already exists");
		ApiResponseMessage response = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST)
				.success(true).build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
