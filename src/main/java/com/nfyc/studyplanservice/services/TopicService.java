package com.nfyc.studyplanservice.services;


import com.nfyc.studyplanservice.model.dto.TopicDTO;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    TopicDTO getTopicByID(UUID topicID);
    List<TopicDTO> getTopicByCourseID(UUID courseID);
    TopicDTO addNewTopicToCourse(TopicDTO topicDTO);
    TopicDTO updateTopic(UUID topicID, TopicDTO topicDTO);
    void deleteTopic(UUID topicID);
}
