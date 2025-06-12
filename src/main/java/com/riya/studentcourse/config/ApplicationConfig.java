package com.riya.studentcourse.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.riya.studentcourse.dto.StudentCourseDTO;
import com.riya.studentcourse.entity.StudentCourse;

@Configuration
public class ApplicationConfig {

 @Bean
 public ModelMapper modelMapper() {
	 
     ModelMapper modelMapper = new ModelMapper();
     modelMapper.createTypeMap(StudentCourse.class, StudentCourseDTO.class)
        .addMapping(src -> src.getId().getStudentId(), StudentCourseDTO::setStudentId)
        .addMapping(src -> src.getId().getCourseId(), StudentCourseDTO::setCourseId);
     return modelMapper;
 }
}