package com.riya.studentcourse.service;

import com.riya.studentcourse.dto.CourseDTO;
import com.riya.studentcourse.dto.EnrolledStudentDTO;
import com.riya.studentcourse.entity.Course;
import com.riya.studentcourse.entity.StudentCourse;
import com.riya.studentcourse.exception.ResourceNotFoundException;
import com.riya.studentcourse.exception.DuplicateResourceException;
import com.riya.studentcourse.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

	 private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
	
	 private final CourseRepository courseRepository;
	 private final ModelMapper modelMapper;
	
	 @Autowired
	 public CourseService(CourseRepository courseRepository, ModelMapper modelMapper) {
	     this.courseRepository = courseRepository;
	     this.modelMapper = modelMapper;
	 }
	
	 @Transactional
	 public CourseDTO createCourse(CourseDTO courseDTO) {
	     logger.info("Creating new course with title: {}", courseDTO.getTitle());
	     
	     if (courseRepository.findByTitle(courseDTO.getTitle()).isPresent()) {
	         logger.warn("Attempted to create course with duplicate title: {}", courseDTO.getTitle());
	         throw new DuplicateResourceException("Course with title " + courseDTO.getTitle() + " already exists.");
	     }
	     Course course = modelMapper.map(courseDTO, Course.class);
	     Course savedCourse = courseRepository.save(course);
	     logger.info("Course created successfully with ID: {}", savedCourse.getId());
	     return modelMapper.map(savedCourse, CourseDTO.class);
	 }
	
	 public CourseDTO getCourseById(Long id) {
	     logger.info("Fetching course with ID: {}", id);
	     Course course = courseRepository.findById(id)
	             .orElseThrow(() -> {
	                 logger.warn("Course not found with ID: {}", id);
	                 return new ResourceNotFoundException("Course not found with ID: " + id);
	             });
	     return modelMapper.map(course, CourseDTO.class);
	 }
	
	 public List<CourseDTO> getAllCourses() {
	     logger.info("Fetching all courses");
	     return courseRepository.findAll().stream()
	             .map(course -> modelMapper.map(course, CourseDTO.class))
	             .collect(Collectors.toList());
	 }
	
	 @Transactional
	 public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
	     logger.info("Updating course with ID: {}", id);
	     Course existingCourse = courseRepository.findById(id)
	             .orElseThrow(() -> {
	                 logger.warn("Course not found for update with ID: {}", id);
	                 return new ResourceNotFoundException("Course not found with ID: " + id);
	             });
	
	     if (!existingCourse.getTitle().equals(courseDTO.getTitle()) &&
	         courseRepository.findByTitle(courseDTO.getTitle()).isPresent()) {
	         logger.warn("Attempted to update course title to an existing title: {}", courseDTO.getTitle());
	         throw new DuplicateResourceException("Title " + courseDTO.getTitle() + " is already used by another course.");
	     }
	
	     existingCourse.setTitle(courseDTO.getTitle());
	     existingCourse.setDescription(courseDTO.getDescription());
	     existingCourse.setCredits(courseDTO.getCredits());
	     Course updatedCourse = courseRepository.save(existingCourse);
	     logger.info("Course updated successfully with ID: {}", updatedCourse.getId());
	     return modelMapper.map(updatedCourse, CourseDTO.class);
	 }
	 
	 @Transactional
	    public void deleteCourse(Long id) {
	        logger.info("Deleting course with ID: {}", id);
	        if (!courseRepository.existsById(id)) {
	            logger.warn("Course not found for deletion with ID: {}", id);
	            throw new ResourceNotFoundException("Course not found with ID: " + id);
	        }
	        courseRepository.deleteById(id);
	        logger.info("Course deleted successfully with ID: {}", id);
	    }

	    public List<EnrolledStudentDTO> getStudentsByCourseId(Long courseId) {
	        logger.info("Fetching students for course with ID: {}", courseId);
	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> {
	                    logger.warn("Course not found when fetching students for ID: {}", courseId);
	                    return new ResourceNotFoundException("Course not found with ID: " + courseId);
	                });

	        return course.getStudentCourses().stream()
	                .map(sc -> {
	                    EnrolledStudentDTO dto = new EnrolledStudentDTO();
	                    dto.setStudentId(sc.getStudent().getId());
	                    dto.setStudentName(sc.getStudent().getName());
	                    dto.setStudentEmail(sc.getStudent().getEmail());
	                    dto.setEnrolledDate(sc.getEnrolledDate());
	                    dto.setMarks(sc.getMarks());
	                    dto.setAttendancePercentage(sc.getAttendancePercentage());
	                    return dto;
	                })
	                .collect(Collectors.toList());
	    }

}