package com.healthy.mapper;

import com.healthy.dto.TrackingRecordCreateDTO;
import com.healthy.dto.TrackingRecordDTO;
import com.healthy.dto.TrackingRecordGoalCreateDTO;
import com.healthy.dto.TrackingRecordGoalDTO;
import com.healthy.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TrackingRecordMapper {
    private final ModelMapper modelMapper;
    public TrackingRecordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public TrackingRecord toTrackingRecordCreateDTO(TrackingRecordCreateDTO trackingRecordDTO) {
        TrackingRecord trackingRecord = modelMapper.map(trackingRecordDTO, TrackingRecord.class);

        // MAPEAR MANUALMENTE LAS LISTAS
        trackingRecord.setGoals(trackingRecordDTO.getGoals().stream()
                .map(this::toGoalTrackingRecordEntity)
                .toList());
        return trackingRecord;
    }

    public TrackingRecordDTO toTrackingRecordDTO(TrackingRecord trackingRecord) {
        TrackingRecordDTO trackingRecordDTO = modelMapper.map(trackingRecord, TrackingRecordDTO.class);

        trackingRecordDTO.setTrackingId(trackingRecord.getId());
        trackingRecordDTO.setValue(trackingRecord.getValue());
        trackingRecordDTO.setNote(trackingRecord.getNote());

        trackingRecordDTO.setGoals(trackingRecord.getGoals().stream()
                .map(this::toTrackingRecordGoalDTO)
                .toList());
        return trackingRecordDTO;
    }

    private Goal toGoalTrackingRecordEntity(TrackingRecordGoalCreateDTO trgDTO){
        Goal goal = modelMapper.map(trgDTO, Goal.class);
        Profile profile = new Profile();
        Habit habit = new Habit();
        Plan plan = new Plan();
        profile.setId(trgDTO.getProfileId());
        habit.setId(trgDTO.getHabitId());
        plan.setId(trgDTO.getPlanId());
        goal.setPlan(plan);
        goal.setHabit(habit);
        goal.setProfile(profile);


        return goal;
    }
    private TrackingRecordGoalDTO toTrackingRecordGoalDTO(Goal goal){
        TrackingRecordGoalDTO dto = modelMapper.map(goal, TrackingRecordGoalDTO.class);
        dto.setUserName(goal.getProfile().getName());
        dto.setHabitTypeName(goal.getHabit().getHabitType().getName());
        dto.setHabitName(goal.getHabit().getName());
        dto.setPlanName(goal.getPlan().getName());
        dto.setPlanStatus(goal.getPlan().getPlanStatus());
        return dto;
    }
}
