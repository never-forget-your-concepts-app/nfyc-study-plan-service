package com.nfyc.studyplanservice.controllers;

import com.nfyc.studyplanservice.model.dto.CourseDTO;
import com.nfyc.studyplanservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CourseManagementController {

    @Autowired
    protected CourseService courseService;

    /**
     * Create new course
     * @param course
     * @return
     */
    @PostMapping(path = "course")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){
        return ResponseEntity.ok(courseService.addNewCourse(course));
    }

    /**
     * Update course
     * @param courseId
     * @param courseDTO
     * @return
     */
    @PutMapping(path ="course/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable UUID courseId ,@RequestBody CourseDTO courseDTO){
        return ResponseEntity.ok(courseService.updateCourse(courseId,courseDTO));
    }

    /**
     * Delete course based on courseId
     * @param courseId
     * @return
     */
    @DeleteMapping(path = "course/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
    /**
     * Get all courses
     * @return
     */
    @GetMapping(path="courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    /**
     * Get course based on course id
     * @param courseId
     * @return
     */
    @GetMapping(path = "course/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable UUID courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }
}
