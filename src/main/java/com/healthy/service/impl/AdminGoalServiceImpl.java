package com.healthy.service.impl;

import com.healthy.dto.GoalCreateDTO;
import com.healthy.dto.GoalDTO;
import com.healthy.exception.ResourceNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminGoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final ProfileRepository profileRepository;
    private final HabitRepository habitRepository;
    private final PlanRepository planRepository;

    @Transactional
    @Override
    public GoalDTO create(GoalCreateDTO goalDTO){
        Goal goal = goalMapper.toGoalCreateDTO(goalDTO);

        Profile profile = profileRepository.findById(goalDTO.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile "+goalDTO.getProfileId()+" not found"));
        Habit habit = habitRepository.findById(goalDTO.getHabitId())
                        .orElseThrow(() -> new ResourceNotFoundException("Habit "+goalDTO.getHabitId()+" not found"));
        Plan plan = planRepository.findById(goalDTO.getPlanId())
                        .orElseThrow(() -> new ResourceNotFoundException("Plan "+goalDTO.getPlanId()+" not found"));

        goal.setProfile(profile);
        goal.setHabit(habit);
        goal.setStartDate(LocalDateTime.now());
        Goal savedGoal = goalRepository.save(goal);
        return goalMapper.toGoalDTO(savedGoal);
    }

    @Transactional
    @Override
    public GoalDTO update(Integer id, GoalCreateDTO updateGoal){
        Goal fromGoalDb = goalRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Goal "+id+" not found"));
        Profile profile = profileRepository.findById(updateGoal.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("Perfil "+updateGoal.getProfileId()+" not found"));
        Habit habit = habitRepository.findById(updateGoal.getHabitId())
                .orElseThrow(() -> new ResourceNotFoundException("Habit "+updateGoal.getHabitId()+" not found"));
        Plan plan = planRepository.findById(updateGoal.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan "+updateGoal.getPlanId()+" not found"));

        fromGoalDb.setProfile(profile);
        fromGoalDb.setHabit(habit);
        fromGoalDb.setPlan(plan);
        fromGoalDb.setStartDate(LocalDateTime.now());

        fromGoalDb = goalRepository.save(fromGoalDb);

        return goalMapper.toGoalDTO(fromGoalDb);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GoalDTO> getAll(){
        List<Goal> goals = goalRepository.findAll();
        return goals.stream()
                .map(goalMapper::toGoalDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public List<GoalDTO> getById(Integer id){
        return goalRepository.findById(id).stream()
                .map(goalMapper::toGoalDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<GoalDTO> paginate(Pageable pageable) {
        return goalRepository.findAll(pageable)
                .map(goalMapper::toGoalDTO);
    }
    public void deleteGoal(Integer id){
        goalRepository.deleteById(id);
    }
}
