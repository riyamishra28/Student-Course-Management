package com.riya.studentcourse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "student_courses")
public class StudentCourse {
	
	 @EmbeddedId
	 private StudentCourseId id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @MapsId("studentId")
	 @JoinColumn(name = "student_id")
	 private Student student;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @MapsId("courseId")
	 @JoinColumn(name = "course_id")
	 private Course course;
	
	 @NotNull(message = "Enrolled date cannot be null")
	 private LocalDate enrolledDate;
	
	 @Min(value = 0, message = "Grade must be at least 0")
	 @Max(value = 100, message = "Grade must be at most 100")
	 private Integer grade; // e.g., 0-100
	
	 @NotNull(message = "Attendance cannot be null")
	 @Min(value = 0, message = "Attendance percentage must be at least 0")
	 @Max(value = 100, message = "Attendance percentage must be at most 100")
	 private Integer attendancePercentage; 
	
	 public StudentCourse() {}
	 
	 public StudentCourse(Student student, Course course, LocalDate enrolledDate, Integer grade, Integer attendancePercentage) {
	     this.student = student;
	     this.course = course;
	     this.enrolledDate = enrolledDate;
	     this.grade = grade;
	     this.attendancePercentage = attendancePercentage;
	     this.id = new StudentCourseId(student.getId(), course.getId());
	 }

	 public StudentCourseId getId() {
	     return id;
	 }
	
	 public void setId(StudentCourseId id) {
	     this.id = id;
	 }
	
	 public Student getStudent() {
	     return student;
	 }
	
	 public void setStudent(Student student) {
	     this.student = student;
	     if (this.id == null) {
	         this.id = new StudentCourseId();
	     }
	     this.id.setStudentId(student.getId());
	 }
	
	 public Course getCourse() {
	     return course;
	 }
	
	 public void setCourse(Course course) {
	     this.course = course;
	     if (this.id == null) {
	         this.id = new StudentCourseId();
	     }
	     this.id.setCourseId(course.getId());
	 }
	
	 public LocalDate getEnrolledDate() {
	     return enrolledDate;
	 }
	
	 public void setEnrolledDate(LocalDate enrolledDate) {
	     this.enrolledDate = enrolledDate;
	 }
	
	 public Integer getGrade() {
	     return grade;
	 }
	
	 public void setGrade(Integer grade) {
	     this.grade = grade;
	 }
	
	 public Integer getAttendancePercentage() {
	     return attendancePercentage;
	 }
	
	 public void setAttendancePercentage(Integer attendancePercentage) {
	     this.attendancePercentage = attendancePercentage;
	 }
	
	 @Override
	 public String toString() {
	     return "StudentCourse{" +
	            "id=" + id +
	            ", enrolledDate=" + enrolledDate +
	            ", grade=" + grade +
	            ", attendancePercentage=" + attendancePercentage +
	            '}';
	 }
}