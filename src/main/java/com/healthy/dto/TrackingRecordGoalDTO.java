package com.healthy.dto;

import com.healthy.model.enums.GoalStatus;
import com.healthy.model.enums.PlanStatus;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
@Data
public class TrackingRecordGoalDTO {
    private Integer id;
    private Float currentValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private GoalStatus goalStatus;

    // MAPEADO DE PROFILE
    private String userName;

    // MAPEADO DE HABITS
    private String habitTypeName;
    private String habitName;

    // MAPEADO DE PLAN
    private String planName;
    private PlanStatus planStatus;
}
