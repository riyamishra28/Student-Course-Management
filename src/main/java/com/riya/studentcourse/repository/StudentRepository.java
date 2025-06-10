package com.riya.studentcourse.repository;

import com.riya.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
 
	Optional<Student> findByEmail(String email);
}