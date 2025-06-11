package com.riya.studentcourse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CourseDTO {

	 private Long id;
	
	 @NotBlank(message = "Title cannot be blank")
	 @Size(max = 200, message = "Title cannot exceed 200 characters")
	 private String title;
	
	 @Size(max = 500, message = "Description cannot exceed 500 characters")
	 private String description;
	
	 @PositiveOrZero(message = "Credits must be a positive number or zero")
	 private Integer credits;
	
	 public CourseDTO() {}
	
	 public CourseDTO(Long id, String title, String description, Integer credits) {
	     this.id = id;
	     this.title = title;
	     this.description = description;
	     this.credits = credits;
	 }
	
	 public Long getId() {
	     return id;
	 }
	
	 public void setId(Long id) {
	     this.id = id;
	 }
	
	 public String getTitle() {
	     return title;
	 }
	
	 public void setTitle(String title) {
	     this.title = title;
	 }
	
	 public String getDescription() {
	     return description;
	 }
	
	 public void setDescription(String description) {
	     this.description = description;
	 }
	
	 public Integer getCredits() {
	     return credits;
	 }
	
	 public void setCredits(Integer credits) {
	     this.credits = credits;
	 }
}
