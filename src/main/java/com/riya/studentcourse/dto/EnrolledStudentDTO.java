package com.riya.studentcourse.dto;

import java.time.LocalDate;

public class EnrolledStudentDTO {
	 private Long studentId;
	 private String studentName;
	 private String studentEmail;
	 private LocalDate enrolledDate;
	 private Integer marks;
	 private Integer attendancePercentage;
	
	 public EnrolledStudentDTO() {
	 }
	
	 public EnrolledStudentDTO(Long studentId, String studentName, String studentEmail, LocalDate enrolledDate, Integer marks, Integer attendancePercentage) {
	     this.studentId = studentId;
	     this.studentName = studentName;
	     this.studentEmail = studentEmail;
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
	
	 public String getStudentName() {
	     return studentName;
	 }
	
	 public void setStudentName(String studentName) {
	     this.studentName = studentName;
	 }
	
	 public String getStudentEmail() {
	     return studentEmail;
	 }
	
	 public void setStudentEmail(String studentEmail) {
	     this.studentEmail = studentEmail;
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