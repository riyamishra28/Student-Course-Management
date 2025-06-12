package com.riya.studentcourse.service;

import com.riya.studentcourse.dto.StudentCourseDTO;
import com.riya.studentcourse.entity.Course;
import com.riya.studentcourse.entity.Student;
import com.riya.studentcourse.entity.StudentCourse;
import com.riya.studentcourse.exception.ResourceNotFoundException;
import com.riya.studentcourse.exception.DuplicateResourceException;
import com.riya.studentcourse.repository.CourseRepository;
import com.riya.studentcourse.repository.StudentCourseRepository;
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
public class StudentCourseService {

	 private static final Logger logger = LoggerFactory.getLogger(StudentCourseService.class);
	
	 private final StudentCourseRepository studentCourseRepository;
	 private final StudentRepository studentRepository;
	 private final CourseRepository courseRepository;
	 private final ModelMapper modelMapper;
	
	 @Autowired
	 public StudentCourseService(StudentCourseRepository studentCourseRepository,
	                             StudentRepository studentRepository,
	                             CourseRepository courseRepository,
	                             ModelMapper modelMapper) {
	     this.studentCourseRepository = studentCourseRepository;
	     this.studentRepository = studentRepository;
	     this.courseRepository = courseRepository;
	     this.modelMapper = modelMapper;
	 }
	
	 @Transactional
	 public StudentCourseDTO enrollStudentInCourse(StudentCourseDTO studentCourseDTO) {
	     logger.info("Enrolling student {} in course {}", studentCourseDTO.getStudentId(), studentCourseDTO.getCourseId());
	
	     Student student = studentRepository.findById(studentCourseDTO.getStudentId())
	             .orElseThrow(() -> {
	                 logger.warn("Student not found for enrollment with ID: {}", studentCourseDTO.getStudentId());
	                 return new ResourceNotFoundException("Student not found with ID: " + studentCourseDTO.getStudentId());
	             });
	
	     Course course = courseRepository.findById(studentCourseDTO.getCourseId())
	             .orElseThrow(() -> {
	                 logger.warn("Course not found for enrollment with ID: {}", studentCourseDTO.getCourseId());
	                 return new ResourceNotFoundException("Course not found with ID: " + studentCourseDTO.getCourseId());
	             });
	
	     // Check if student is already enrolled in the course
	     if (studentCourseRepository.findByStudentIdAndCourseId(student.getId(), course.getId()).isPresent()) {
	         logger.warn("Student {} is already enrolled in course {}", student.getId(), course.getId());
	         throw new DuplicateResourceException("Student " + student.getId() + " is already enrolled in course " + course.getId());
	     }
	
	     StudentCourse studentCourse = new StudentCourse(student, course,
	                                                     studentCourseDTO.getEnrolledDate(),
	                                                     studentCourseDTO.getMarks(),
	                                                     studentCourseDTO.getAttendancePercentage());
	
	     studentCourse.setStudent(student); 
	     studentCourse.setCourse(course); 
	
	     StudentCourse savedStudentCourse = studentCourseRepository.save(studentCourse);
	     logger.info("Student {} enrolled in course {} successfully.", student.getId(), course.getId());
	     return modelMapper.map(savedStudentCourse, StudentCourseDTO.class);
	 }
	
	 public StudentCourseDTO getEnrollmentDetails(Long studentId, Long courseId) {
	     logger.info("Fetching enrollment details for student {} and course {}", studentId, courseId);
	     StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
	             .orElseThrow(() -> {
	                 logger.warn("Enrollment not found for student {} and course {}", studentId, courseId);
	                 return new ResourceNotFoundException("Enrollment not found for student " + studentId + " and course " + courseId);
	             });
	     return modelMapper.map(studentCourse, StudentCourseDTO.class);
	 }
	
	 @Transactional
	 public StudentCourseDTO updateEnrollment(Long studentId, Long courseId, StudentCourseDTO studentCourseDTO) {
	     logger.info("Updating enrollment details for student {} and course {}", studentId, courseId);
	     StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
	             .orElseThrow(() -> {
	                 logger.warn("Enrollment not found for update for student {} and course {}", studentId, courseId);
	                 return new ResourceNotFoundException("Enrollment not found for student " + studentId + " and course " + courseId);
	             });
	
	     
	     studentCourse.setEnrolledDate(studentCourseDTO.getEnrolledDate());
	     studentCourse.setMarks(studentCourseDTO.getMarks());
	     studentCourse.setAttendancePercentage(studentCourseDTO.getAttendancePercentage());
	
	     StudentCourse updatedStudentCourse = studentCourseRepository.save(studentCourse);
	     logger.info("Enrollment for student {} and course {} updated successfully.", studentId, courseId);
	     return modelMapper.map(updatedStudentCourse, StudentCourseDTO.class);
	 }
	
	 @Transactional
	 public void unenrollStudentFromCourse(Long studentId, Long courseId) {
	     logger.info("Unenrolling student {} from course {}", studentId, courseId);
	     StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
	             .orElseThrow(() -> {
	                 logger.warn("Enrollment not found for unenrollment for student {} and course {}", studentId, courseId);
	                 return new ResourceNotFoundException("Enrollment not found for student " + studentId + " and course " + courseId);
	             });
	     studentCourseRepository.delete(studentCourse);
	     logger.info("Student {} unenrolled from course {} successfully.", studentId, courseId);
	 }
}