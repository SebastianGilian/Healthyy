package com.healthy.service.impl;

import com.healthy.dto.TrackingRecordCreateDTO;
import com.healthy.dto.TrackingRecordDTO;
import com.healthy.mapper.TrackingRecordMapper;
import com.healthy.model.entity.TrackingRecord;
import com.healthy.repository.GoalRepository;
import com.healthy.repository.TrackingRecordRepository;
import com.healthy.service.TrackingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackingRecordServiceImpl implements TrackingRecordService {

    private final TrackingRecordRepository trackingRecordRepository;
    private final TrackingRecordMapper trackingRecordMapper;

    @Transactional(readOnly = true)
    @Override
    public TrackingRecordDTO createTrackingRecord(TrackingRecordCreateDTO trackingRecordCreateDTO){
        TrackingRecord trackingRecord = trackingRecordMapper.toTrackingRecordCreateDTO(trackingRecordCreateDTO);
        trackingRecord.setDate(LocalDateTime.now());
        TrackingRecord savedTrackingRecord = trackingRecordRepository.save(trackingRecord);
        return trackingRecordMapper.toTrackingRecordDTO(savedTrackingRecord);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TrackingRecordDTO> getAllTrackingRecords(){
        List<TrackingRecord> trackingRecords = trackingRecordRepository.findAll();
        return trackingRecords.stream()
                .map(trackingRecordMapper::toTrackingRecordDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public List<TrackingRecordDTO> getTrackingRecordsByGoal(Integer id){
        return trackingRecordRepository.findById(id).stream()
                .map(trackingRecordMapper::toTrackingRecordDTO)
                .toList();
    }

    public void deleteTrackingRecord(Integer id){
        TrackingRecord trackingRecord = trackingRecordRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No record found with id " + id));
        trackingRecordRepository.delete(trackingRecord);
    }
}
