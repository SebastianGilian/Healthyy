package com.healthy.api;

import com.healthy.dto.TrackingRecordCreateDTO;
import com.healthy.dto.TrackingRecordDTO;
import com.healthy.service.TrackingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/trackingrecords")
public class TrackingRecordController {
    private final TrackingRecordService trackingRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TrackingRecordDTO>> getTrackingRecordsByGoalID(@PathVariable("id")Integer id){
        List<TrackingRecordDTO> tr = trackingRecordService.getTrackingRecordsByGoal(id);
        return ResponseEntity.ok(tr);
    }
    @PostMapping
    public ResponseEntity<TrackingRecordDTO> createTrackingRecord(@RequestBody TrackingRecordCreateDTO trackingRecordDTO){
        TrackingRecordDTO tr = trackingRecordService.createTrackingRecord(trackingRecordDTO);
        return new ResponseEntity<>(tr, HttpStatus.CREATED);
    }
}
