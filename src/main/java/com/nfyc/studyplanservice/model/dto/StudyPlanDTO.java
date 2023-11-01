package com.nfyc.studyplanservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyPlanDTO {
    @JsonProperty("course")
    private CourseDTO courseDTO;
    @JsonProperty("topics")
    private Set<TopicDTO> topicDTOS;
}
