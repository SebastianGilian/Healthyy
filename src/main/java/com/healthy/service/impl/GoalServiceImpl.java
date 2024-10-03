package com.healthy.service.impl;

import com.healthy.dto.GoalCreateDTO;
import com.healthy.dto.GoalDTO;
import com.healthy.mapper.GoalMapper;
import com.healthy.model.entity.Goal;
import com.healthy.model.entity.Habit;
import com.healthy.model.entity.Plan;
import com.healthy.model.entity.Profile;
import com.healthy.repository.GoalRepository;
import com.healthy.repository.HabitRepository;
import com.healthy.repository.PlanRepository;
import com.healthy.repository.ProfileRepository;
import com.healthy.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final ProfileRepository profileRepository;
    private final HabitRepository habitRepository;
    private final PlanRepository planRepository;

    @Transactional
    @Override
    public GoalDTO createGoal(GoalCreateDTO goalDTO){
        Goal goal = goalMapper.toGoalCreateDTO(goalDTO);

        Profile profile = profileRepository.findById(goalDTO.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        Habit habit = habitRepository.findById(goalDTO.getHabitId())
                        .orElseThrow(() -> new RuntimeException("Habit not found"));
        Plan plan = planRepository.findById(goalDTO.getPlanId())
                        .orElseThrow(() -> new RuntimeException("Plan not found"));

        goal.setProfile(profile);
        goal.setHabit(habit);
        goal.setPlan(plan);
        goal.setStartDate(LocalDateTime.now());
        Goal savedGoal = goalRepository.save(goal);
        return goalMapper.toGoalDTO(savedGoal);
    }
    @Transactional(readOnly = true)
    @Override
    public List<GoalDTO> getAllGoals(){
        List<Goal> goals = goalRepository.findAll();
        return goals.stream()
                .map(goalMapper::toGoalDTO)
                .toList();
    }
    public List<GoalDTO> getGoalByID(Integer id){
        return goalRepository.findById(id).stream()
                .map(goalMapper::toGoalDTO)
                .toList();

    }
    public void deleteGoal(Integer id){
        goalRepository.deleteById(id);
    }
}
