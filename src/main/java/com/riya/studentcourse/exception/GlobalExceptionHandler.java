package com.riya.studentcourse.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	 private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
	         ResourceNotFoundException ex, WebRequest request) {
	     logger.error("ResourceNotFoundException: {}", ex.getMessage(), ex);
	     ErrorResponse errorResponse = new ErrorResponse(
	             HttpStatus.NOT_FOUND.value(),
	             HttpStatus.NOT_FOUND.getReasonPhrase(),
	             ex.getMessage(),
	             request.getDescription(false)
	     );
	     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	 }
	
	 @ExceptionHandler(DuplicateResourceException.class)
	 public ResponseEntity<ErrorResponse> handleDuplicateResourceException(
	         DuplicateResourceException ex, WebRequest request) {
	     logger.error("DuplicateResourceException: {}", ex.getMessage(), ex);
	     ErrorResponse errorResponse = new ErrorResponse(
	             HttpStatus.CONFLICT.value(),
	             HttpStatus.CONFLICT.getReasonPhrase(),
	             ex.getMessage(),
	             request.getDescription(false)
	     );
	     return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	 }
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ErrorResponse> handleValidationExceptions(
	         MethodArgumentNotValidException ex, WebRequest request) {
	     logger.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
	     Map<String, String> errors = new HashMap<>();
	     ex.getBindingResult().getAllErrors().forEach(error -> {
	         String fieldName = ((FieldError) error).getField();
	         String errorMessage = error.getDefaultMessage();
	         errors.put(fieldName, errorMessage);
	     });
	
	     ErrorResponse errorResponse = new ErrorResponse(
	             HttpStatus.BAD_REQUEST.value(),
	             HttpStatus.BAD_REQUEST.getReasonPhrase(),
	             "Validation Failed",
	             request.getDescription(false),
	             errors
	     );
	     return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	 }
	
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ErrorResponse> handleGlobalException(
	         Exception ex, WebRequest request) {
	     logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
	     ErrorResponse errorResponse = new ErrorResponse(
	             HttpStatus.INTERNAL_SERVER_ERROR.value(),
	             HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
	             "An unexpected error occurred: " + ex.getMessage(),
	             request.getDescription(false)
	     );
	     return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
