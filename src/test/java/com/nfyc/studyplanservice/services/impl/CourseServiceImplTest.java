package com.nfyc.studyplanservice.services.impl;

import com.nfyc.studyplanservice.mappers.CourseMapper;
import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Test
    public void testGetCourseById() {
        UUID courseId = UUID.randomUUID();
        Course course = new Course();
        course.setCourseID(courseId);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseID(courseId);

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);

        CourseDTO result = courseService.getCourseById(courseId);

        assertEquals(courseDTO, result);
    }

    @Test
    public void testGetCourseByIdNotFound() {
        UUID courseId = UUID.randomUUID();

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> courseService.getCourseById(courseId));
    }

    @Test
    public void testAddNewCourse() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setTopics(Collections.emptyList());
        Course course = new Course();

        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);

        CourseDTO result = courseService.addNewCourse(courseDTO);

        assertEquals(courseDTO, result);
        Mockito.verify(courseRepository).save(course);
    }

    @Test
    public void testUpdateCourse() {
        UUID courseId = UUID.randomUUID();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseID(courseId);
        Course existingCourse = new Course();
        existingCourse.setCourseID(courseId);

        Mockito.when(courseMapper.courseToCourseDTO(Mockito.any())).thenReturn(courseDTO);
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));

        CourseDTO updatedCourseDTO = courseService.updateCourse(courseId, courseDTO);

        assertEquals(courseDTO, updatedCourseDTO);
        Mockito.verify(courseRepository).save(existingCourse);
    }

    @Test
    public void testUpdateCourseInvalidId() {
        UUID courseId = UUID.randomUUID();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseID(UUID.randomUUID()); // Mismatched ID

        assertThrows(RuntimeException.class, () -> courseService.updateCourse(courseId, courseDTO));
    }

    @Test
    public void testUpdateCourseNotFound() {
        UUID courseId = UUID.randomUUID();
        assertThrows(RuntimeException.class, () -> courseService.updateCourse(courseId, new CourseDTO()));
    }

    @Test
    public void testDeleteCourse() {
        UUID courseId = UUID.randomUUID();
        Course course = new Course();

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        courseService.deleteCourse(courseId);

        Mockito.verify(courseRepository).delete(course);
    }

    @Test
    public void testDeleteCourseNotFound() {
        UUID courseId = UUID.randomUUID();

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> courseService.deleteCourse(courseId));
    }
}
