package com.healthy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrackingRecordDTO {
    private Integer trackingId;
    private LocalDateTime date;
    private Float value;
    private String note;
    private List<TrackingRecordGoalDTO> goals;
}
