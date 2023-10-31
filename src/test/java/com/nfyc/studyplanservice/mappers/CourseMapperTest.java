package com.nfyc.studyplanservice.mappers;

import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {
    private final CourseMapper courseMapper = CourseMapper.INSTANCE;
    @Test
    void courseToCourseDTO() {
        Course course = Course.builder()
                .courseID(UUID.randomUUID())
                .courseName("Mathematics")
                .isRevised(true)
                .lastRevised(new Timestamp(System.currentTimeMillis()))
                .build();

        // Map the Course entity to CourseDTO
        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);

        // Verify the mapping
        assertEquals(course.getCourseID(), courseDTO.getCourseID());
        assertEquals(course.getCourseName(), courseDTO.getCourseName());
    }
    @Test
    void courseDTOToCourse() {
        CourseDTO courseDTO = CourseDTO.builder()
                .courseID(UUID.randomUUID())
                .courseName("History")
                .isRevised(false)
                .lastRevised(new Timestamp(System.currentTimeMillis()))
                .build();
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        assertEquals(courseDTO.getCourseID(), course.getCourseID());
        assertEquals(courseDTO.getCourseName(), course.getCourseName());
    }
}