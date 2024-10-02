package com.healthy.repository;

import com.healthy.model.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Integer>{

}
