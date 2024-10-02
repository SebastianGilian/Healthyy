package com.healthy.service;

import com.healthy.dto.TrackingRecordCreateDTO;
import com.healthy.dto.TrackingRecordDTO;

import java.util.List;

public interface TrackingRecordService {
    TrackingRecordDTO createTrackingRecord(TrackingRecordCreateDTO trackingRecordDTO);
    List<TrackingRecordDTO> getAllTrackingRecords();
    List<TrackingRecordDTO> getTrackingRecordsByGoal(Integer id);
    void deleteTrackingRecord(Integer id);
}
