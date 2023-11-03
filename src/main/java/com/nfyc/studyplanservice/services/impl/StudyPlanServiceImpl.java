package com.nfyc.studyplanservice.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.nfyc.studyplanservice.mappers.CourseMapper;
import com.nfyc.studyplanservice.mappers.TopicMapper;
import com.nfyc.studyplanservice.model.dto.FilterRequestListDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.repositories.TopicRepository;
import com.nfyc.studyplanservice.services.CourseService;
import com.nfyc.studyplanservice.services.StudyPlanService;
import com.nfyc.studyplanservice.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyPlanServiceImpl implements StudyPlanService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final TopicMapper topicMapper;

    private final ObjectMapper objectMapper;

    private final Validator validator;

    @Override
    public StudyPlanListDTO getStudyPlan(JsonNode requestBody) {

        FilterRequestListDTO filterRequestListDTO = validateAndGetStudyPlanFilter(requestBody);

        return StudyPlanListDTO.builder().studyPlanDTOList(courseRepository.findAll().stream().map(course ->
                StudyPlanDTO.builder().courseDTO(courseMapper.courseToCourseDTO(course))
                        .topicDTOS(course.getTopics().stream()
                                .map(topicMapper::topicToTopicDTO).collect(Collectors.toSet())).build()).collect(Collectors.toList())).build();
    }

    @Override
    public FilterRequestListDTO validateAndGetStudyPlanFilter(JsonNode requestBody) {
        JsonNode filter = requestBody.get("data").get("filter");
        if (filter == null){
            throw new RuntimeException("Filter was found Null");
        }
        String filterCondition = requestBody.get("data").get("filter").fieldNames().next();

        if (!Objects.equals(filterCondition, "and") && !Objects.equals(filterCondition, "or")) {
            throw new RuntimeException("Filter Condition is invalid. It can either be and / or");
        }

        if (!filter.get(filterCondition).isArray()) {
            throw new RuntimeException("Given Condition " + filterCondition + " is not an array");
        }
        TypeReference<FilterRequestListDTO> typeReference = new TypeReference<>() {};
        FilterRequestListDTO filterRequestListDTO = objectMapper.convertValue(requestBody.get("data").get("filter"), typeReference);
        filterRequestListDTO.setFilterCondition(filterCondition);
        Set<ConstraintViolation<FilterRequestListDTO>> violations = validator.validate(filterRequestListDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<FilterRequestListDTO> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
        return filterRequestListDTO;
    }
}
