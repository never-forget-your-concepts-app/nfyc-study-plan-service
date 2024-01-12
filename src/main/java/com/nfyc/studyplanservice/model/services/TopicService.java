package com.nfyc.studyplanservice.model.services;


import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.model.dto.TopicDTO;

import java.util.List;
import java.util.UUID;

public interface TopicService {
  TopicDTO getTopicByID(UUID topicID) throws NyfcException;

  List<TopicDTO> getTopicByCourseID(UUID courseID) throws NyfcException;

  TopicDTO addNewTopicToCourse(TopicDTO topicDTO) throws NyfcException;

  TopicDTO updateTopic(UUID topicID, TopicDTO topicDTO) throws NyfcException;

  void deleteTopic(UUID topicID) throws NyfcException;
}
