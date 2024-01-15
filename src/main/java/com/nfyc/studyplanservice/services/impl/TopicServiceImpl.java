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
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.nfyc.studyplanservice.utl.StudyPlanUtil.isValidUUID;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

  private final CourseRepository courseRepository;
  private final TopicRepository topicRepository;
  private final TopicMapper topicMapper;

  @Override
  public TopicDTO getTopicByID(UUID topicID) throws NyfcException {

    try {
      if (isValidUUID(topicID)) {
        return topicRepository.findById(topicID).map(topicMapper::topicToTopicDTO).
          orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "topic", "id"));
      } else {
        throw new NyfcException(ErrorCode.NYFC_ERR_REQUEST_INVALID);
      }
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }

  }

  @Override
  @Transactional
  public List<TopicDTO> getTopicByCourseID(UUID courseID) throws NyfcException {
    try {
      //TODO call from curse and return topics to reduce 2 calls
      if (isValidUUID(courseID) && ifCoursedExists(courseID)) {
        return topicRepository.findTopicsByCourse_CourseID(courseID).stream()
          .map(topicMapper::topicToTopicDTO).collect(Collectors.toList());
      } else {
        throw new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id");
      }
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
        new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id"));
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
      if (isValidUUID(topicID)) {
        return topicRepository.findById(topicID).map(topic -> {
            topic.setTopicName(topicDTO.getTopicName());
            topic.setPriority(topicDTO.getPriority());
            return topicRepository.save(topic);
          }).map(topicMapper::topicToTopicDTO)
          .orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "course", "id"));
      } else {
        throw new NyfcException(ErrorCode.NYFC_ERR_REQUEST_INVALID);
      }
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }

  }

  @Override
  public void deleteTopic(UUID topicID) throws NyfcException {
    try {
      if (isValidUUID(topicID)) {
        Topic retrievedTopic = topicRepository.findById(topicID)
          .orElseThrow(() -> new NyfcException(ErrorCode.NYFC_ERR_NOT_FOUND, "topic", "id"));
        retrievedTopic.getCourse().removeTopic(retrievedTopic);
      } else {
        throw new NyfcException(ErrorCode.NYFC_ERR_REQUEST_INVALID);
      }
    } catch (NyfcException e) {
      throw e;
    } catch (Exception exception) {
      throw new NyfcException(ErrorCode.NYFC_ERR_DATABASE_EXCEPTION, exception.getMessage());
    }
  }

  private boolean ifCoursedExists(UUID courseId){
    return courseRepository.existsById(courseId);
  }
}
