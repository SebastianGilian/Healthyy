package com.healthy.service;

import com.healthy.dto.PlanCreateDTO;
import com.healthy.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO createPlan(PlanCreateDTO planDTO);
    List<PlanDTO> getAllPlans();
    List<PlanDTO> getByProfileID(Integer profileID);

}
