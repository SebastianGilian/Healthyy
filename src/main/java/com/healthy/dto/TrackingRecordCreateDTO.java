package com.healthy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrackingRecordCreateDTO {
    private LocalDateTime date;
    private Float value;
    private String note;
    private List<TrackingRecordGoalCreateDTO> goals;
}
