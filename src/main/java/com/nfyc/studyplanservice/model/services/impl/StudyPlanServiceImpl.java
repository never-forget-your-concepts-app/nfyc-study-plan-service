package com.nfyc.studyplanservice.model.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfyc.studyplanservice.mappers.CourseMapper;
import com.nfyc.studyplanservice.mappers.TopicMapper;
import com.nfyc.studyplanservice.repositories.specifications.CourseSpecifications;
import com.nfyc.studyplanservice.model.dto.FilterRequestListDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.model.services.StudyPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyPlanServiceImpl implements StudyPlanService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;
  private final TopicMapper topicMapper;

  private final ObjectMapper objectMapper;

  private final Validator validator;

  @Cacheable(cacheNames = "studyPlanListCache", condition = "#requestBody.isEmpty()")
  @Override
  public StudyPlanListDTO getStudyPlan(JsonNode requestBody, int pageNo, int pageSize) {
    log.info("Fetching Study Plan");
    Pageable page = PageRequest.of(pageNo, pageSize);
    FilterRequestListDTO filterRequestListDTO = validateAndGetStudyPlanFilter(requestBody);
    return StudyPlanListDTO.builder()
      .studyPlanDTOList(courseRepository.findAll(CourseSpecifications.applyCourseFilters(filterRequestListDTO))
        .stream().map(course ->
          StudyPlanDTO.builder().courseDTO(courseMapper.courseToCourseDTO(course))
            .topicDTOS(course.getTopics().stream()
              .map(topicMapper::topicToTopicDTO).collect(Collectors.toSet())).build()).collect(Collectors.toList()))
      .build();
  }

  @Override
  public FilterRequestListDTO validateAndGetStudyPlanFilter(JsonNode requestBody) {
    if (requestBody == null || !requestBody.has("data")) {
      return FilterRequestListDTO.builder().filters(Collections.emptyList()).filterCondition("and").build();
    }
    JsonNode filter = requestBody.get("data").get("filter");
    if (filter == null) {
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
    FilterRequestListDTO filterRequestListDTO = objectMapper.convertValue(requestBody.get("data").get("filter"),
      typeReference);
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
