package com.nfyc.studyplanservice.services.impl;

import com.nfyc.studyplanservice.mappers.CourseMapper;
import com.nfyc.studyplanservice.mappers.TopicMapper;
import com.nfyc.studyplanservice.model.dto.StudyPlanDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import com.nfyc.studyplanservice.repositories.TopicRepository;
import com.nfyc.studyplanservice.services.CourseService;
import com.nfyc.studyplanservice.services.StudyPlanService;
import com.nfyc.studyplanservice.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyPlanServiceImpl implements StudyPlanService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final TopicMapper topicMapper;

    @Override
    public StudyPlanListDTO getStudyPlan() {
        return StudyPlanListDTO.builder().studyPlanDTOList(courseRepository.findAll().stream().map(course ->
                StudyPlanDTO.builder().courseDTO(courseMapper.courseToCourseDTO(course))
                        .topicDTOS(course.getTopics().stream()
                                .map(topicMapper::topicToTopicDTO).collect(Collectors.toSet())).build()).collect(Collectors.toList())).build();
    }
}
