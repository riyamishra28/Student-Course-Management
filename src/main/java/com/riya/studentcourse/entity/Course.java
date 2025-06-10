package com.riya.studentcourse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @NotBlank(message = "Title cannot be blank")
	 @Size(max = 200, message = "Title cannot exceed 200 characters")
	 @Column(unique = true)
	 private String title;
	
	 @Size(max = 500, message = "Description cannot exceed 500 characters")
	 private String description;
	
	 @PositiveOrZero(message = "Credits must be a positive number or zero")
	 private Integer credits;
	
	 //"owning" side
	 @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	 private Set<StudentCourse> studentCourses = new HashSet<>();
	
	 public Course() {}
	
	 public Course(String title, String description, Integer credits) {
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
	
	 public Set<StudentCourse> getStudentCourses() {
	     return studentCourses;
	 }
	
	 public void setStudentCourses(Set<StudentCourse> studentCourses) {
	     this.studentCourses = studentCourses;
	 }
	
	 public void addStudentCourse(StudentCourse studentCourse) {
	     this.studentCourses.add(studentCourse);
	     studentCourse.setCourse(this);
	 }
	
	 public void removeStudentCourse(StudentCourse studentCourse) {
	     this.studentCourses.remove(studentCourse);
	     studentCourse.setCourse(null);
	 }
	
	 @Override
	 public String toString() {
	     return "Course{" +
	            "id=" + id +
	            ", title='" + title + '\'' +
	            ", description='" + description + '\'' +
	            ", credits=" + credits +
	            '}';
	 }
}