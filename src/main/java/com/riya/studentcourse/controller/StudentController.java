package com.riya.studentcourse.controller;

import com.riya.studentcourse.dto.StudentDTO;
import com.riya.studentcourse.dto.EnrolledCourseDTO;
import com.riya.studentcourse.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	 private final StudentService studentService;
	
	 @Autowired
	 public StudentController(StudentService studentService) {
	     this.studentService = studentService;
	 }
	
	 @PostMapping
	 public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
	     StudentDTO createdStudent = studentService.createStudent(studentDTO);
	     return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	 }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
	     StudentDTO student = studentService.getStudentById(id);
	     return ResponseEntity.ok(student);
	 }
	
	 @GetMapping
	 public ResponseEntity<List<StudentDTO>> getAllStudents() {
	     List<StudentDTO> students = studentService.getAllStudents();
	     return ResponseEntity.ok(students);
	 }
	
	 @PutMapping("/{id}")
	 public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
	     StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
	     return ResponseEntity.ok(updatedStudent);
	 }
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	     studentService.deleteStudent(id);
	     return ResponseEntity.noContent().build();
	 }
	
	 @GetMapping("/{studentId}/courses")
	 public ResponseEntity<List<EnrolledCourseDTO>> getCoursesEnrolledByStudent(@PathVariable Long studentId) {
	     List<EnrolledCourseDTO> courses = studentService.getCoursesByStudentId(studentId);
	     return ResponseEntity.ok(courses);
	 }
}
