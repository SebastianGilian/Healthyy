package com.healthy.api;

import com.healthy.dto.GoalCreateDTO;
import com.healthy.dto.GoalDTO;
import com.healthy.model.entity.Goal;
import com.healthy.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("goals")
public class GoalController {
    private final GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<List<GoalDTO>> getGoalById(@PathVariable("id") Integer id) {
        List<GoalDTO> goal = goalService.getGoalByID(id);
        return ResponseEntity.ok(goal);
    }
    @GetMapping
    public ResponseEntity<List<GoalDTO>> getAllGoals() {
        List<GoalDTO> goal = goalService.getAllGoals();
        return ResponseEntity.ok(goal);
    }
    @PostMapping
    public ResponseEntity<GoalDTO> createGoal(@RequestBody GoalCreateDTO goalDTO) {
        GoalDTO goal = goalService.createGoal(goalDTO);
        return new ResponseEntity<>(goal, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable("id") Integer id) {
        goalService.deleteGoal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
