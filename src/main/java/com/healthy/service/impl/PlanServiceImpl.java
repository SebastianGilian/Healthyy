package com.healthy.service.impl;

import com.healthy.dto.PlanCreateDTO;
import com.healthy.dto.PlanDTO;
import com.healthy.mapper.PlanMapper;
import com.healthy.model.entity.Goal;
import com.healthy.model.entity.Plan;
import com.healthy.model.entity.Profile;
import com.healthy.repository.GoalRepository;
import com.healthy.repository.PlanRepository;
import com.healthy.repository.ProfileRepository;
import com.healthy.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;
    private final ProfileRepository profileRepository;
    private final GoalRepository goalRepository;

    @Transactional
    @Override
    public PlanDTO createPlan(PlanCreateDTO planCreateDTO) {
        Plan plan = planMapper.toPlanCreateDTO(planCreateDTO);

        Profile profile = profileRepository.findById(planCreateDTO.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        plan.setProfile(profile);

        return planMapper.toPlanDTO(planRepository.save(plan));
    }
    @Transactional(readOnly = true)
    @Override
    public List<PlanDTO> getAllPlans(){
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(planMapper::toPlanDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlanDTO> getByProfileID(Integer id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return profile.getPlans().stream()
                .map(planMapper::toPlanDTO)
                .toList();
    }
}
