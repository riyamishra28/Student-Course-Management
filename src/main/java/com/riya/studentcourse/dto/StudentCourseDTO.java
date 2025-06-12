package com.riya.studentcourse.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class StudentCourseDTO {

	 @NotNull(message = "Student ID cannot be null")
	 private Long studentId;
	
	 @NotNull(message = "Course ID cannot be null")
	 private Long courseId;
	
	 @NotNull(message = "Enrolled date cannot be null")
	 private LocalDate enrolledDate;
	
	 @Min(value = 0, message = "marks must be at least 0")
	 @Max(value = 100, message = "marks must be at most 100")
	 private Integer marks;
	
	 @NotNull(message = "Attendance cannot be null")
	 @Min(value = 0, message = "Attendance percentage must be at least 0")
	 @Max(value = 100, message = "Attendance percentage must be at most 100")
	 private Integer attendancePercentage;
	
	 public StudentCourseDTO() {}
	
	 public StudentCourseDTO(Long studentId, Long courseId, LocalDate enrolledDate, Integer marks, Integer attendancePercentage) {
	     this.studentId = studentId;
	     this.courseId = courseId;
	     this.enrolledDate = enrolledDate;
	     this.marks = marks;
	     this.attendancePercentage = attendancePercentage;
	 }
	
	 public Long getStudentId() {
	     return studentId;
	 }
	
	 public void setStudentId(Long studentId) {
	     this.studentId = studentId;
	 }
	
	 public Long getCourseId() {
	     return courseId;
	 }
	
	 public void setCourseId(Long courseId) {
	     this.courseId =courseId;
	 }
	
	 public LocalDate getEnrolledDate() {
	     return enrolledDate;
	 }
	
	 public void setEnrolledDate(LocalDate enrolledDate) {
	     this.enrolledDate = enrolledDate;
	 }
	
	
	 public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public Integer getAttendancePercentage() {
	     return attendancePercentage;
	 }
	
	 public void setAttendancePercentage(Integer attendancePercentage) {
	     this.attendancePercentage = attendancePercentage;
	 }
}