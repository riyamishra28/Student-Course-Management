package com.riya.studentcourse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @NotBlank(message = "Name cannot be blank")
	 @Size(max = 100, message = "Name cannot exceed 100 characters")
	 private String name;
	
	 @NotBlank(message = "Email cannot be blank")
	 @Email(message = "Email should be valid")
	 @Column(unique = true)
	 private String email;
	
	 @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	 private Set<StudentCourse> studentCourses = new HashSet<>();
	
	 public Student() {}
	
	 public Student(String name, String email) {
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
	
	 public Set<StudentCourse> getStudentCourses() {
	     return studentCourses;
	 }
	
	 public void setStudentCourses(Set<StudentCourse> studentCourses) {
	     this.studentCourses = studentCourses;
	 }
	
	 public void addStudentCourse(StudentCourse studentCourse) {
	     this.studentCourses.add(studentCourse);
	     studentCourse.setStudent(this);
	 }
	 
	public void removeStudentCourse(StudentCourse studentCourse) {
	     this.studentCourses.remove(studentCourse);
	     studentCourse.setStudent(null);
	 }
	
	 @Override
	 public String toString() {
	     return "Student{" +
	            "id=" + id +
	            ", name='" + name + '\'' +
	            ", email='" + email + '\'' +
	            '}';
	 }
}
