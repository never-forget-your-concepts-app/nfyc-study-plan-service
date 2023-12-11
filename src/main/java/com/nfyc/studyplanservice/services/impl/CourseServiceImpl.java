package com.nfyc.studyplanservice.services.impl;

import com.nfyc.studyplanservice.mappers.CourseMapper;
import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public Page<CourseDTO> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseDTO> courseDTOPage=courseRepository.findAll(pageable).stream().map(courseMapper::courseToCourseDTO).collect(Collectors.collectingAndThen(Collectors.toList(), PageImpl::new));
        return courseDTOPage;
    }

    @Override
    public CourseDTO getCourseById(UUID courseID) {
        return courseRepository.findById(courseID).map(courseMapper :: courseToCourseDTO).orElseThrow(() ->
                new RuntimeException("Course Not Found"));

    }

    @Override
    public CourseDTO addNewCourse(CourseDTO courseDTO) {
        Course courseToAdd = courseMapper.courseDTOToCourse(courseDTO);
        courseRepository.save(courseToAdd);
        return courseDTO;
    }

    @Override
    public CourseDTO updateCourse(UUID courseID, CourseDTO courseDTO) {
        if (!courseID.equals(courseDTO.getCourseID())){
            throw new RuntimeException("Invalid ID Provided");
        }
        return courseRepository.findById(courseID).map(
                course -> {
                    course.setCourseName(courseDTO.getCourseName());
                    Course updatedCourse = courseRepository.save(course);
                    return courseMapper.courseToCourseDTO(updatedCourse);
                }
        ).orElseThrow(() -> new RuntimeException("Course ID Not Found"));
    }

    @Override
    public void deleteCourse(UUID courseID) {
        Course retrievedCourse = courseRepository.findById(courseID).orElseThrow(() -> new RuntimeException("Course Not Found"));
        courseRepository.delete(retrievedCourse);
    }
}
