package com.riya.studentcourse.repository;

import com.riya.studentcourse.entity.StudentCourse;
import com.riya.studentcourse.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

	 List<StudentCourse> findByStudentId(Long studentId);
	
	 List<StudentCourse> findByCourseId(Long courseId);
	
	 Optional<StudentCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
}