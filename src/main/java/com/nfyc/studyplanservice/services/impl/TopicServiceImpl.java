package com.nfyc.studyplanservice.services.impl;

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
    public TopicDTO getTopicByID(UUID topicID) {
        return topicRepository.findById(topicID).map(topicMapper::topicToTopicDTO).
                orElseThrow(() -> new RuntimeException("Topic Not Found"));
    }

    @Override
    public  List<TopicDTO> getTopicByCourseID(UUID courseID){
        return topicRepository.findTopicsByCourse_CourseID(courseID).stream().map(topicMapper::topicToTopicDTO).collect(Collectors.toList());
    }

    @Override
    public TopicDTO addNewTopicToCourse(TopicDTO topicDTO) {
        //Topic is created under a course.
        Course course = courseRepository.findById(topicDTO.getCourseID()).orElseThrow(() ->
                new RuntimeException("Course Not Found for the topic"));
        Topic createdTopic = topicMapper.topicDTOToTopic(topicDTO);
        course.addTopic(createdTopic);
        //Persist the course, topic would automatically be persisted.
        courseRepository.save(course);
        TopicDTO updatedTopic = topicMapper.topicToTopicDTO(createdTopic);
        return updatedTopic;
    }

    @Override
    public TopicDTO updateTopic(UUID topicID, TopicDTO topicDTO) {
        return topicRepository.findById(topicID).map(topic -> {
            topic.setTopicName(topicDTO.getTopicName());
            topic.setPriority(topicDTO.getPriority());
            return topicRepository.save(topic);
        }).map(topicMapper::topicToTopicDTO).orElseThrow(() -> new RuntimeException("Topic Not Found"));
    }

    @Override
    public void deleteTopic(UUID topicID) {
        Topic retrievedTopic = topicRepository.findById(topicID).orElseThrow(() -> new RuntimeException("Topic not found to delete"));
        retrievedTopic.getCourse().removeTopic(retrievedTopic);
        topicRepository.save(retrievedTopic);
    }
}
