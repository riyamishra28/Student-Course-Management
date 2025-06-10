package com.riya.studentcourse.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
	 private LocalDateTime timestamp;
	 private int status;
	 private String error;
	 private String message;
	 private String path;
	 private Map<String, String> fieldErrors; // For validation errors
	
	 public ErrorResponse() {
	     this.timestamp = LocalDateTime.now();
	 }
	
	 public ErrorResponse(int status, String error, String message, String path) {
	     this();
	     this.status = status;
	     this.error = error;
	     this.message = message;
	     this.path = path;
	 }
	
	 public ErrorResponse(int status, String error, String message, String path, Map<String, String> fieldErrors) {
	     this(status, error, message, path);
	     this.fieldErrors = fieldErrors;
	 }
	
	 public LocalDateTime getTimestamp() {
	     return timestamp;
	 }
	
	 public void setTimestamp(LocalDateTime timestamp) {
	     this.timestamp = timestamp;
	 }
	
	 public int getStatus() {
	     return status;
	 }
	
	 public void setStatus(int status) {
	     this.status = status;
	 }
	
	 public String getError() {
	     return error;
	 }
	
	 public void setError(String error) {
	     this.error = error;
	 }
	
	 public String getMessage() {
	     return message;
	 }
	
	 public void setMessage(String message) {
	     this.message = message;
	 }
	
	 public String getPath() {
	     return path;
	 }
	
	 public void setPath(String path) {
	     this.path = path;
	 }
	
	 public Map<String, String> getFieldErrors() {
	     return fieldErrors;
	 }
	
	 public void setFieldErrors(Map<String, String> fieldErrors) {
	     this.fieldErrors = fieldErrors;
	 }
}