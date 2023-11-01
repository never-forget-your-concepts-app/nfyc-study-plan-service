package com.nfyc.studyplanservice.controllers;

import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import com.nfyc.studyplanservice.services.StudyPlanService;
import com.nfyc.studyplanservice.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/")
public class StudyPlanController {

    @Autowired
    private StudyPlanService studyPlanService;

    @PostMapping(path = "studyPlan")
    public ResponseEntity<StudyPlanListDTO> getStudyPlan() {
        return ResponseEntity.ok(studyPlanService.getStudyPlan());
    }
}
