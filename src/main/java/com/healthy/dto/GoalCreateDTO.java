package com.healthy.dto;

import com.healthy.model.enums.GoalStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoalCreateDTO {
    private Integer profileId;
    private Integer habitId;
    private Integer planId;

    private Float targetValue;
    private Float currentValue;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private GoalStatus status;
    private List<GoalTrackingRecordCreateDTO> trackings;
}
