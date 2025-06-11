package com.riya.studentcourse.controller;

import com.riya.studentcourse.dto.StudentCourseDTO;
import com.riya.studentcourse.service.StudentCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class StudentCourseController {

	 private final StudentCourseService ser;
	
	 @Autowired
	 public StudentCourseController(StudentCourseService studentCourseService) {
	     this.ser = studentCourseService;
	 }
	
	 @PostMapping
	 public ResponseEntity<StudentCourseDTO> enrollStudentInCourse(@Valid @RequestBody StudentCourseDTO studentCourseDTO) {
	     StudentCourseDTO enrolled = ser.enrollStudentInCourse(studentCourseDTO);
	     return new ResponseEntity<>(enrolled, HttpStatus.CREATED);
	 }
	
	 @GetMapping("/{studentId}/{courseId}")
	 public ResponseEntity<StudentCourseDTO> getEnrollmentDetails(@PathVariable Long studentId, @PathVariable Long courseId) {
	     StudentCourseDTO enrollment = ser.getEnrollmentDetails(studentId, courseId);
	     return ResponseEntity.ok(enrollment);
	 }
	
	 @PutMapping("/{studentId}/{courseId}")
	 public ResponseEntity<StudentCourseDTO> updateEnrollment(@PathVariable Long studentId, @PathVariable Long courseId,
	                                                        @Valid @RequestBody StudentCourseDTO studentCourseDTO) {
	     StudentCourseDTO updated = ser.updateEnrollment(studentId, courseId, studentCourseDTO);
	     return ResponseEntity.ok(updated);
	 }
	
	 @DeleteMapping("/{studentId}/{courseId}")
	 public ResponseEntity<Void> unenrollStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
	     ser.unenrollStudentFromCourse(studentId, courseId);
	     return ResponseEntity.noContent().build();
	 }
}
