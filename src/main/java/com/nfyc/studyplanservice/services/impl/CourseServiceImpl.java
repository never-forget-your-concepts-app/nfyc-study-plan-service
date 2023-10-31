package com.nfyc.studyplanservice.services.impl;

import com.nfyc.studyplanservice.model.Course;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.services.CourseService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Override
    public Optional<Course> getCourseById(UUID courseID) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> addNewCourse(Course course) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> updateCourse(Course course) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> deleteCourse(Course course) {
        return Optional.empty();
    }
}
