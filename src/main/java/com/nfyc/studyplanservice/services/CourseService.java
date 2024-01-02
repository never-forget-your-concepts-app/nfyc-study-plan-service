package com.nfyc.studyplanservice.services;


import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.model.dto.CourseDTO;

import java.util.List;
import java.util.UUID;


public interface CourseService {

    List<CourseDTO> getAllCourses() throws NyfcException;
    CourseDTO getCourseById(UUID courseID);

    CourseDTO addNewCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(UUID uuid, CourseDTO courseDT);

    void deleteCourse(UUID uuid);
}
