package com.nfyc.studyplanservice.services;

import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.model.dto.CourseDTO;

import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CourseService {

  CourseDTO addNewCourse(CourseDTO courseDT) throws NyfcException;

  Page<CourseDTO> getAllCourses(int page, int size) throws NyfcException;

  CourseDTO getCourseById(UUID courseID) throws NyfcException;

  CourseDTO updateCourse(UUID uuid, CourseDTO courseDT) throws NyfcException;

  void deleteCourse(UUID uuid) throws NyfcException;
}
