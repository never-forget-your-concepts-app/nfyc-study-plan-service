package com.nfyc.studyplanservice.services;


import com.nfyc.studyplanservice.model.dto.CourseDTO;
import com.nfyc.studyplanservice.model.dto.TopicDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TopicService {
    TopicDTO getTopicByID(UUID topicID);
    TopicDTO addNewTopicToCourse(TopicDTO topicDTO);
    TopicDTO updateTopic(UUID topicID, TopicDTO topicDTO);
    void deleteTopic(UUID topicID);
}
