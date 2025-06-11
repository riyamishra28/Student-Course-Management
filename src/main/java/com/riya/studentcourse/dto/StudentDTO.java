package com.riya.studentcourse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentDTO {

	 private Long id;
	
	 @NotBlank(message = "Name cannot be blank")
	 @Size(max = 100, message = "Name cannot exceed 100 characters")
	 private String name;
	
	 @NotBlank(message = "Email cannot be blank")
	 @Email(message = "Email should be valid")
	 private String email;
	
	 public StudentDTO() {}
	
	 public StudentDTO(Long id, String name, String email) {
	     this.id = id;
	     this.name = name;
	     this.email = email;
	 }
	
	 public Long getId() {
	     return id;
	 }
	
	 public void setId(Long id) {
	     this.id = id;
	 }
	
	 public String getName() {
	     return name;
	 }
	
	 public void setName(String name) {
	     this.name = name;
	 }
	
	 public String getEmail() {
	     return email;
	 }
	
	 public void setEmail(String email) {
	     this.email = email;
	 }
}
