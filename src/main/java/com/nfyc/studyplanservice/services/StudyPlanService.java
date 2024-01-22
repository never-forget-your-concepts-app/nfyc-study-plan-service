package com.nfyc.studyplanservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.nfyc.studyplanservice.exception.NyfcException;
import com.nfyc.studyplanservice.model.dto.FilterRequestListDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;

public interface StudyPlanService {
  StudyPlanListDTO getStudyPlan(JsonNode request, int pageNumber, int pageSize) throws NyfcException;

  FilterRequestListDTO validateAndGetStudyPlanFilter(JsonNode request) throws NyfcException;
}
