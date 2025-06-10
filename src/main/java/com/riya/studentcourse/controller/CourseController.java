package com.riya.studentcourse.controller;

import com.riya.studentcourse.dto.CourseDTO;
import com.riya.studentcourse.dto.EnrolledStudentDTO;
import com.riya.studentcourse.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	 private final CourseService courseService;
	
	 @Autowired
	 public CourseController(CourseService courseService) {
	     this.courseService = courseService;
	 }
	
	 @PostMapping
	 public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
	     CourseDTO createdCourse = courseService.createCourse(courseDTO);
	     return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
	 }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
	     CourseDTO course = courseService.getCourseById(id);
	     return ResponseEntity.ok(course);
	 }
	
	 @GetMapping
	 public ResponseEntity<List<CourseDTO>> getAllCourses() {
	     List<CourseDTO> courses = courseService.getAllCourses();
	     return ResponseEntity.ok(courses);
	 }
	
	 @PutMapping("/{id}")
	 public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
	     CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
	     return ResponseEntity.ok(updatedCourse);
	 }
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
	     courseService.deleteCourse(id);
	     return ResponseEntity.noContent().build();
	 }
	
	 @GetMapping("/{courseId}/students")
	 public ResponseEntity<List<EnrolledStudentDTO>> getStudentsEnrolledInCourse(@PathVariable Long courseId) {
	     List<EnrolledStudentDTO> students = courseService.getStudentsByCourseId(courseId);
	     return ResponseEntity.ok(students);
	 }
}