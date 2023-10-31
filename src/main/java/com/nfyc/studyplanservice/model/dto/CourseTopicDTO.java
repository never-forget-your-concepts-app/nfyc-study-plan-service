package com.nfyc.studyplanservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseTopicDTO {

    private CourseDTO courseDTO;
    private List<TopicDTO> topicDTOS;
}
