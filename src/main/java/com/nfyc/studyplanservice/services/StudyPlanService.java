package com.nfyc.studyplanservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.nfyc.studyplanservice.model.dto.FilterRequestListDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import org.springframework.stereotype.Service;

public interface StudyPlanService {
    StudyPlanListDTO getStudyPlan(JsonNode request);
    FilterRequestListDTO validateAndGetStudyPlanFilter(JsonNode request);
}