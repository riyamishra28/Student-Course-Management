package com.riya.studentcourse.dto;

import java.time.LocalDate;

public class EnrolledCourseDTO {
	 private Long courseId;
	 private String courseTitle;
	 private String courseDescription;
	 private Integer courseCredits;
	 private LocalDate enrolledDate;
	 private Integer marks;
	 private Integer attendancePercentage;
	
	 public EnrolledCourseDTO() {}
	
	 public EnrolledCourseDTO(Long courseId, String courseTitle, String courseDescription, Integer courseCredits, LocalDate enrolledDate, Integer marks, Integer attendancePercentage) {
	     this.courseId = courseId;
	     this.courseTitle = courseTitle;
	     this.courseDescription = courseDescription;
	     this.courseCredits = courseCredits;
	     this.enrolledDate = enrolledDate;
	     this.marks = marks;
	     this.attendancePercentage = attendancePercentage;
	 }
	
	 public Long getCourseId() {
	     return courseId;
	 }
	
	 public void setCourseId(Long courseId) {
	     this.courseId = courseId;
	 }
	
	 public String getCourseTitle() {
	     return courseTitle;
	 }
	
	 public void setCourseTitle(String courseTitle) {
	     this.courseTitle = courseTitle;
	 }
	
	 public String getCourseDescription() {
	     return courseDescription;
	 }
	
	 public void setCourseDescription(String courseDescription) {
	     this.courseDescription = courseDescription;
	 }
	
	 public Integer getCourseCredits() {
	     return courseCredits;
	 }
	
	 public void setCourseCredits(Integer courseCredits) {
	     this.courseCredits = courseCredits;
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
