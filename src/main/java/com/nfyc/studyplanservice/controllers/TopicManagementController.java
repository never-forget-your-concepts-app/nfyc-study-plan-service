package com.nfyc.studyplanservice.controllers;

import com.nfyc.studyplanservice.model.dto.TopicDTO;
import com.nfyc.studyplanservice.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TopicManagementController {

    @Autowired
    protected TopicService topicService;

    /**
     * Create new topic
     * @param topicDTO
     * @return
     */
    @PostMapping(path = "topic")
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDTO){
        return ResponseEntity.ok(topicService.addNewTopicToCourse(topicDTO));
    }

    /**
     * Update course
     * @param topicId
     * @param topicDTO
     * @return
     */
    @PutMapping(path ="topic/{topicId}")
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable UUID topicId , @RequestBody TopicDTO topicDTO){
        return ResponseEntity.ok(topicService.updateTopic(topicId,topicDTO));
    }

    /**
     * Delete topic based on courseId
     * @param topicId
     * @return
     */
    @DeleteMapping(path = "topic/{topicId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID topicId){
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get topic based on topic id
     * @param topicId
     * @return
     */
    @GetMapping(path = "topic/{topicId}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable UUID topicId){
        return ResponseEntity.ok(topicService.getTopicByID(topicId));
    }


    /**
     * Get topic based on course id
     * @param courseId
     * @return
     */
    @GetMapping(path = "topic/course/{courseId}")
    public ResponseEntity<List<TopicDTO>> getTopicByCourseId(@PathVariable UUID courseId){
        return ResponseEntity.ok(topicService.getTopicByCourseID(courseId));
    }
}
