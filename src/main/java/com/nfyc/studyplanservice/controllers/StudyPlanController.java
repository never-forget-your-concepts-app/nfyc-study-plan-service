package com.nfyc.studyplanservice.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import com.nfyc.studyplanservice.model.services.StudyPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StudyPlanController {

  private final StudyPlanService studyPlanService;

  @PostMapping(path = "studyPlan")
  public ResponseEntity<StudyPlanListDTO> getStudyPlan(
    @RequestBody
    JsonNode requestBody,
    @RequestParam(defaultValue = "0")
    int pageNo,
    @RequestParam(defaultValue = "10")
    int pageSize) {

    return ResponseEntity.ok(studyPlanService.getStudyPlan(requestBody, pageNo, pageSize));
  }

}
