package com.healthy.api;

import com.healthy.dto.PlanCreateDTO;
import com.healthy.dto.PlanDTO;
import com.healthy.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/plans")
public class AdminPlanController {
    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAll() {
        List<PlanDTO> plan = planService.getAll();
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PlanDTO>> getPage(@PageableDefault(size=5) Pageable pageable) {
        Page<PlanDTO> plan = planService.paginate(pageable);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getById(@PathVariable Integer id) {
        PlanDTO plan = planService.findById(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> create(@RequestBody PlanCreateDTO planDTO) {
        PlanDTO plan = planService.create(planDTO);
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> update(@PathVariable Integer id,
                                                            @Valid @RequestBody PlanCreateDTO planFromDTO) {
        PlanDTO updatedPlan = planService.update(id, planFromDTO);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanDTO> delete(@PathVariable("id") Integer id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
