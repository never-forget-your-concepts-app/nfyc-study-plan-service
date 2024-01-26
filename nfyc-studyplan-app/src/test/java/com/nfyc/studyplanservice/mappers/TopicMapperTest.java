package com.nfyc.studyplanservice.mappers;

import com.nfyc.domain.Course;
import com.nfyc.domain.Topic;
import com.nfyc.studyplanservice.model.dto.TopicDTO;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TopicMapperTest {

    private final TopicMapper topicMapper = TopicMapper.INSTANCE;
    @Test
    void topicToTopicDTO() {

        Course course = Course.builder()
                .courseID(UUID.randomUUID())
                .courseName("Mathematics")
                .isRevised(true)
                .lastRevised(new Timestamp(System.currentTimeMillis()))
                .build();

        Topic topic = Topic.builder()
                .topicID(UUID.randomUUID())
                .topicName("Algebra Basics")
                .priority(1)
                .lastRevised(new Timestamp(System.currentTimeMillis()))
                .course(course)
                .build();

        TopicDTO topicDTO = topicMapper.topicToTopicDTO(topic);
        assertEquals(topic.getTopicID(), topicDTO.getTopicID());
        assertEquals(topic.getTopicName(), topicDTO.getTopicName());
        assertEquals(topic.getCourse().getCourseID(), topicDTO.getCourseID());
    }

    @Test
    void topicDTOToTopic() {
        TopicDTO topicDTO = TopicDTO.builder()
                .topicID(UUID.randomUUID())
                .topicName("World War I")
                .priority(2)
                .lastRevised(new Timestamp(System.currentTimeMillis()))
                .courseID(UUID.randomUUID())
                .build();
        Topic topic = topicMapper.topicDTOToTopic(topicDTO);
        assertEquals(topicDTO.getTopicID(), topic.getTopicID());
        assertEquals(topicDTO.getTopicName(), topic.getTopicName());
    }

}