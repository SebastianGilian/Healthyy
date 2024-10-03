package com.healthy.repository;

import com.healthy.model.entity.Plan;
import com.healthy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}