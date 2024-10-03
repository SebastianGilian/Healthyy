package com.healthy.api;

import com.healthy.dto.PlanCreateDTO;
import com.healthy.dto.PlanDTO;
import com.healthy.repository.PlanRepository;
import com.healthy.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("plans")
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAllPlans() {
        List<PlanDTO> plan = planService.getAllPlans();
        return ResponseEntity.ok(plan);
    }
    @PostMapping
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanCreateDTO planDTO) {
        PlanDTO plan = planService.createPlan(planDTO);
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }
}
