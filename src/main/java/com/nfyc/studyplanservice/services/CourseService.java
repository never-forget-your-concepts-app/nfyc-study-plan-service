package com.nfyc.studyplanservice.services;


import com.nfyc.studyplanservice.model.domain.Course;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CourseService {
    Optional<Course> getCourseById(UUID courseID);

    Optional<Course> addNewCourse(Course course);

    Optional<Course> updateCourse(Course course);

    Optional<Course> deleteCourse(Course course);
}
