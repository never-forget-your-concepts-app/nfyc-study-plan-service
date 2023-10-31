package com.nfyc.studyplanservice.services;


import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CourseService {

    CourseDTO getCourseById(UUID courseID);

    CourseDTO addNewCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(UUID uuid, CourseDTO courseDT);

    void deleteCourse(UUID uuid);
}
