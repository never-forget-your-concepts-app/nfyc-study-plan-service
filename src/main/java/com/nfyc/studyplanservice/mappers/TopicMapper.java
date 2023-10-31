package com.nfyc.studyplanservice.mappers;

import com.nfyc.studyplanservice.model.domain.Topic;
import com.nfyc.studyplanservice.model.dto.TopicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    @Mapping(target = "courseID", source = "course.courseID")
    TopicDTO topicToTopicDTO(Topic topic);

    @Mapping(target = "creationDate", ignore = true)
    Topic topicDTOToTopic(TopicDTO topicDTO);
}
