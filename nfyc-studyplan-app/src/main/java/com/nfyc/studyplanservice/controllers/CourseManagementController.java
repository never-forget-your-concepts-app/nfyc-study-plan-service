package com.nfyc.studyplanservice.controllers;

import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import com.nfyc.studyplanservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CourseManagementController {


  private final CourseService courseService;

  /**
   * Create new course
   *
   * @param course
   * @return
   */
  @PostMapping(path = "course")
  public ResponseEntity<CourseDTO> createCourse(
    @RequestBody
    CourseDTO course) throws NyfcException {
    return ResponseEntity.ok(courseService.addNewCourse(course));
  }

  /**
   * Update course
   *
   * @param courseId
   * @param courseDTO
   * @return
   */
  @PutMapping(path = "course/{courseId}")
  public ResponseEntity<CourseDTO> updateCourse(
    @PathVariable
    UUID courseId,
    @RequestBody
    CourseDTO courseDTO) throws NyfcException {
    return ResponseEntity.ok(courseService.updateCourse(courseId, courseDTO));
  }

  /**
   * Delete course based on courseId
   *
   * @param courseId
   * @return
   */
  @DeleteMapping(path = "course/{courseId}")
  public ResponseEntity<Void> deleteCourse(
    @PathVariable
    UUID courseId) throws NyfcException {
    courseService.deleteCourse(courseId);
    return ResponseEntity.ok().build();
  }

  /**
   * Get all courses
   *
   * @return
   */
  @GetMapping(path = "courses")

  public ResponseEntity<Page<CourseDTO>> getAllCourses(
    @RequestParam(defaultValue = "0")
    int page,
    @RequestParam(defaultValue = "10")
    int size) throws NyfcException {
    return ResponseEntity.ok(courseService.getAllCourses(page, size));
  }

  /**
   * Get course based on course id
   *
   * @param courseId
   * @return
   */
  @GetMapping(path = "course/{courseId}")
  public ResponseEntity<CourseDTO> getCourseById(
    @PathVariable
    UUID courseId) throws NyfcException {
    return ResponseEntity.ok(courseService.getCourseById(courseId));
  }
}
