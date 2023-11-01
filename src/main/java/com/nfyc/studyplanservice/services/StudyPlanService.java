package com.nfyc.studyplanservice.services;

import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import org.springframework.stereotype.Service;

public interface StudyPlanService {
    StudyPlanListDTO getStudyPlan();
}