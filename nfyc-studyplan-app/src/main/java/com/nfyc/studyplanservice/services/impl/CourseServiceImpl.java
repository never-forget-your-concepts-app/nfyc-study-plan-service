package com.nfyc.studyplanservice.services.impl;

import com.nfyc.domain.Course;
import com.nfyc.studyplanservice.exception.ErrorCode;
import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.mappers.CourseMapper;
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

  public Page<CourseDTO> getAllCourses(int page, int size) throws NyfcException {
    try {
      Pageable pageable = PageRequest.of(page, size);
      Page<CourseDTO> courseDTOPage = courseRepository.findAll(pageable).stream()
        .map(courseMapper::courseToCourseDTO)
        .collect(Collectors.collectingAndThen(Collectors.toList(), PageImpl::new));
      return courseDTOPage;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }
  }

  @Override
  public CourseDTO getCourseById(UUID courseID) throws NyfcException {
    try {
      return courseRepository.findById(courseID).map(courseMapper::courseToCourseDTO)
        .orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id", courseID.toString()));

    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }

  }

  @Override
  public CourseDTO addNewCourse(CourseDTO courseDTO) throws NyfcException {
    try {
      courseDTO.getTopics().stream().forEach(topicDTO -> {
        topicDTO.setCourseID(courseDTO.getCourseID());
      });
      Course courseToAdd = courseMapper.courseDTOToCourse(courseDTO);
      courseToAdd.getTopics().forEach(topic -> topic.setCourse(courseToAdd));
      courseRepository.save(courseToAdd);
      return courseDTO;
    } catch (Exception e) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, e.getMessage());
    }
  }

  @Override
  public CourseDTO updateCourse(UUID courseID, CourseDTO courseDTO) throws NyfcException {
    if (!courseID.equals(courseDTO.getCourseID())) {
      throw new NyfcException(ErrorCode.NYFC_ERR_REQUEST_INVALID);
    }
    try {
      return courseRepository.findById(courseID).map(
        course -> {
          course.setCourseName(courseDTO.getCourseName());
          Course updatedCourse = courseRepository.save(course);
          return courseMapper.courseToCourseDTO(updatedCourse);
        }).orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id", courseID.toString()));
    } catch (Exception e) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, e.getMessage());
    }
  }

  @Override
  public void deleteCourse(UUID courseID) throws NyfcException {
    try {
      Course retrievedCourse = courseRepository.findById(courseID)
        .orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id", courseID.toString()));
      courseRepository.delete(retrievedCourse);
    } catch (Exception e) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, e.getMessage());
    }

  }
}
