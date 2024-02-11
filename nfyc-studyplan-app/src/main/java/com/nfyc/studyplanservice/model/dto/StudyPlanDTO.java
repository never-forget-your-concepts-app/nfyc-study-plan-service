package com.nfyc.studyplanservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyPlanDTO implements Serializable {
    @JsonProperty("course")
    private CourseDTO courseDTO;
    @JsonProperty("topics")
    private Set<TopicDTO> topicDTOS;
}
