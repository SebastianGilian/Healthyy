package com.healthy.service;

import com.healthy.dto.GoalCreateDTO;
import com.healthy.dto.GoalDTO;

import java.util.List;

public interface GoalService {
    GoalDTO createGoal(GoalCreateDTO goalDTO);
    List<GoalDTO> getAllGoals();
    List<GoalDTO> getGoalByID(Integer id);
    void deleteGoal(Integer id);
}
