package com.nfyc.studyplanservice.services.impl;

import com.nfyc.domain.Course;
import com.nfyc.domain.Topic;
import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.mappers.TopicMapper;
import com.nfyc.studyplanservice.model.dto.TopicDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.repositories.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private TopicMapper topicMapper;

    @Test
    public void testGetTopicById() throws NyfcException {
        UUID topicId = UUID.randomUUID();
        Topic topic = new Topic();
        topic.setTopicID(topicId);
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopicID(topicId);

        Mockito.when(topicRepository.findById(topicId)).thenReturn(Optional.of(topic));
        Mockito.when(topicMapper.topicToTopicDTO(topic)).thenReturn(topicDTO);

        TopicDTO result = topicService.getTopicByID(topicId);

        assertEquals(topicDTO, result);
    }

    @Test
    public void testGetTopicByIdNotFound() {
        UUID topicId = UUID.randomUUID();

        Mockito.when(topicRepository.findById(topicId)).thenReturn(Optional.empty());

        assertThrows(NyfcException.class, () -> topicService.getTopicByID(topicId));
    }

    @Test
    public void testAddNewTopicToCourse() throws NyfcException {
        UUID courseId = UUID.randomUUID();
        Course course = new Course();
        course.setCourseID(courseId);

        UUID topicID = UUID.randomUUID();

        TopicDTO topicDTOPayload = new TopicDTO();
        topicDTOPayload.setTopicID(topicID);
        topicDTOPayload.setCourseID(courseId);


        Topic topic = new Topic();
        topic.setTopicID(topicID);
        topic.setCourse(course);

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(topicMapper.topicDTOToTopic(Mockito.any(TopicDTO.class))).thenReturn(topic);
        Mockito.when(topicMapper.topicToTopicDTO(Mockito.any(Topic.class))).thenReturn(topicDTOPayload);
        TopicDTO result = topicService.addNewTopicToCourse(topicDTOPayload);

        assertEquals(topicDTOPayload, result);
        Mockito.verify(courseRepository).save(course);
    }

    @Test
    public void testAddNewTopicToCourseCourseNotFound() {
        UUID courseId = UUID.randomUUID();
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setCourseID(courseId);

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(NyfcException.class, () -> topicService.addNewTopicToCourse(topicDTO));
    }

    @Test
    public void testUpdateTopic() throws NyfcException {
        UUID topicId = UUID.randomUUID();
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopicID(topicId);

        Topic existingTopic = new Topic();
        existingTopic.setTopicID(topicId);

        Mockito.when(topicRepository.findById(topicId)).thenReturn(Optional.of(existingTopic));
        Mockito.when(topicRepository.save(Mockito.any())).thenReturn(existingTopic);
        Mockito.when(topicMapper.topicToTopicDTO(Mockito.any(Topic.class))).thenReturn(topicDTO);

        TopicDTO updatedTopicDTO = topicService.updateTopic(topicId, topicDTO);

        assertEquals(topicDTO, updatedTopicDTO);
        Mockito.verify(topicRepository).save(existingTopic);
    }

    @Test
    public void testUpdateTopicNotFound() {
        UUID topicId = UUID.randomUUID();

        Mockito.when(topicRepository.findById(topicId)).thenReturn(Optional.empty());

        assertThrows(NyfcException.class, () -> topicService.updateTopic(topicId, new TopicDTO()));
    }

    @Test
    public void testDeleteTopic() throws NyfcException {
        UUID topicId = UUID.randomUUID();
        Topic topic = new Topic();
        Course course = new Course();
        course.addTopic(topic);
        System.out.println(course.getTopics());
        Mockito.when(topicRepository.findById(topicId)).thenReturn(Optional.of(topic));
        topicService.deleteTopic(topicId);
    }

    @Test
    public void testDeleteTopicNotFound() {
        UUID topicId = UUID.randomUUID();

        Mockito.when(topicRepository.findById(topicId)).thenReturn(Optional.empty());

        assertThrows(NyfcException.class, () -> topicService.deleteTopic(topicId));
    }
}
