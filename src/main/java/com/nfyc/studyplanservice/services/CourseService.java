package com.nfyc.studyplanservice.services;


import com.nfyc.studyplanservice.model.dto.CourseDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface CourseService {

    Page<CourseDTO> getAllCourses(int page,int size);
    CourseDTO getCourseById(UUID courseID);

    CourseDTO addNewCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(UUID uuid, CourseDTO courseDT);

    void deleteCourse(UUID uuid);
}
