package com.nfyc.studyplanservice.services.impl;

import com.nfyc.studyplanservice.exception.ErrorCode;
import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.mappers.TopicMapper;
import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.domain.Topic;
import com.nfyc.studyplanservice.model.dto.TopicDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.repositories.TopicRepository;
import com.nfyc.studyplanservice.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

  private final CourseRepository courseRepository;
  private final TopicRepository topicRepository;
  private final TopicMapper topicMapper;

  @Override
  public TopicDTO getTopicByID(UUID topicID) throws NyfcException {

    try {
      return topicRepository.findById(topicID).map(topicMapper::topicToTopicDTO).
        orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "topic", "id"));
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }

  }

  @Override
  public List<TopicDTO> getTopicByCourseID(UUID courseID) throws NyfcException {
    try {
      Course course = courseRepository.findById(courseID)
        .orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND
          , "course", "id", courseID.toString()));
      return course.getTopics().stream()
        .map(topicMapper::topicToTopicDTO).collect(Collectors.toList());
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }

  }

  @Override
  public TopicDTO addNewTopicToCourse(TopicDTO topicDTO) throws NyfcException {
    try {
      //Topic is created under a course.
      Course course = courseRepository.findById(topicDTO.getCourseID()).orElseThrow(() ->
        new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id", topicDTO.getCourseID().toString()));
      Topic createdTopic = topicMapper.topicDTOToTopic(topicDTO);
      course.addTopic(createdTopic);
      //Persist the course, topic would automatically be persisted.
      courseRepository.save(course);
      TopicDTO updatedTopic = topicMapper.topicToTopicDTO(createdTopic);
      return updatedTopic;
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }
  }

  @Override
  public TopicDTO updateTopic(UUID topicID, TopicDTO topicDTO) throws NyfcException {
    try {
      return topicRepository.findById(topicID).map(topic -> {
          topic.setTopicName(topicDTO.getTopicName());
          topic.setPriority(topicDTO.getPriority());
          return topicRepository.save(topic);
        }).map(topicMapper::topicToTopicDTO)
        .orElseThrow(
          () -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id", topicDTO.getCourseID().toString()));
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }

  }

  @Override
  public void deleteTopic(UUID topicID) throws NyfcException {
    try {
      Topic retrievedTopic = topicRepository.findById(topicID)
        .orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "topic", "id", topicID.toString()));
      retrievedTopic.getCourse().removeTopic(retrievedTopic);
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }
  }

}
