package com.riya.studentcourse.service;

import com.riya.studentcourse.dto.StudentDTO;
import com.riya.studentcourse.dto.EnrolledCourseDTO;
import com.riya.studentcourse.entity.Student;
import com.riya.studentcourse.exception.ResourceNotFoundException;
import com.riya.studentcourse.exception.DuplicateResourceException;
import com.riya.studentcourse.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

	 private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	 private final StudentRepository studentRepository;
	 private final ModelMapper modelMapper;
	
	 @Autowired
	 public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
	     this.studentRepository = studentRepository;
	     this.modelMapper = modelMapper;
	 }
	
	 @Transactional
	 public StudentDTO createStudent(StudentDTO studentDTO) {
	     logger.info("Creating new student with email: {}", studentDTO.getEmail());
	     if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
	         logger.warn("Attempted to create student with duplicate email: {}", studentDTO.getEmail());
	         throw new DuplicateResourceException("Student with email " + studentDTO.getEmail() + " already exists.");
	     }
	     Student student = modelMapper.map(studentDTO, Student.class);
	     Student savedStudent = studentRepository.save(student);
	     logger.info("Student created successfully with ID: {}", savedStudent.getId());
	     return modelMapper.map(savedStudent, StudentDTO.class);
	 }
	
	 public StudentDTO getStudentById(Long id) {
	     logger.info("Fetching student with ID: {}", id);
	     Student student = studentRepository.findById(id)
	             .orElseThrow(() -> {
	                 logger.warn("Student not found with ID: {}", id);
	                 return new ResourceNotFoundException("Student not found with ID: " + id);
	             });
	     return modelMapper.map(student, StudentDTO.class);
	 }
	
	 public List<StudentDTO> getAllStudents() {
	     logger.info("Fetching all students");
	     return studentRepository.findAll().stream()
	             .map(student -> modelMapper.map(student, StudentDTO.class))
	             .collect(Collectors.toList());
	 }
	
	 @Transactional
	 public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
	     logger.info("Updating student with ID: {}", id);
	     Student existingStudent = studentRepository.findById(id)
	             .orElseThrow(() -> {
	                 logger.warn("Student not found for update with ID: {}", id);
	                 return new ResourceNotFoundException("Student not found with ID: " + id);
	             });
	
	     if (!existingStudent.getEmail().equals(studentDTO.getEmail()) &&
	         studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
	         logger.warn("Attempted to update student email to an existing email: {}", studentDTO.getEmail());
	         throw new DuplicateResourceException("Email " + studentDTO.getEmail() + " is already used by another student.");
	     }
	
	     existingStudent.setName(studentDTO.getName());
	     existingStudent.setEmail(studentDTO.getEmail());
	     
	     Student updatedStudent = studentRepository.save(existingStudent);
	     
	     logger.info("Student updated successfully with ID: {}", updatedStudent.getId());
	     
	     return modelMapper.map(updatedStudent, StudentDTO.class);
	 }
	
	 @Transactional
	 public void deleteStudent(Long id) {
	     logger.info("Deleting student with ID: {}", id);
	     if (!studentRepository.existsById(id)) {
	         logger.warn("Student not found for deletion with ID: {}", id);
	         throw new ResourceNotFoundException("Student not found with ID: " + id);
	     }
	     studentRepository.deleteById(id);
	     logger.info("Student deleted successfully with ID: {}", id);
	 }
	
	 public List<EnrolledCourseDTO> getCoursesByStudentId(Long studentId) {
	     logger.info("Fetching courses for student with ID: {}", studentId);
	     Student student = studentRepository.findById(studentId)
	             .orElseThrow(() -> {
	                 logger.warn("Student not found when fetching courses for ID: {}", studentId);
	                 return new ResourceNotFoundException("Student not found with ID: " + studentId);
	             });
	
	     return student.getStudentCourses().stream()
	             .map(sc -> {
	                 EnrolledCourseDTO dto = new EnrolledCourseDTO();
	                 dto.setCourseId(sc.getCourse().getId());
	                 dto.setCourseTitle(sc.getCourse().getTitle());
	                 dto.setCourseDescription(sc.getCourse().getDescription());
	                 dto.setCourseCredits(sc.getCourse().getCredits());
	                 dto.setEnrolledDate(sc.getEnrolledDate());
	                 dto.setMarks(sc.getMarks());
	                 dto.setAttendancePercentage(sc.getAttendancePercentage());
	                 return dto;
	             })
	             .collect(Collectors.toList());
	 }
}