package com.riya.studentcourse.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentCourseId implements Serializable {

	 private Long studentId;
	 private Long courseId;
	
	 public StudentCourseId() {}
	
	 public StudentCourseId(Long studentId, Long courseId) {
	     this.studentId = studentId;
	     this.courseId = courseId;
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
	     this.courseId = courseId;
	 }
	
	 @Override
	 public boolean equals(Object object) {
	     if (this == object) return true;
	     if (object == null || getClass() != object.getClass()) return false;
	     StudentCourseId that = (StudentCourseId) object;
	     return Objects.equals(studentId, that.studentId) &&
	            Objects.equals(courseId, that.courseId);
	 }
	
	 @Override
	 public int hashCode() {
	     return Objects.hash(studentId, courseId);
	 }
}
