package com.healthy.dto;

import com.healthy.model.enums.GoalStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class TrackingRecordGoalCreateDTO {
    private Integer id;
    private Float targetValue;
    private Float currentValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private GoalStatus goalStatus;

    //Profile
    private Integer profileId;
    //Habit
    private Integer habitId;
    private Integer habitTypeId;
    //Plan
    private Integer planId;


}
