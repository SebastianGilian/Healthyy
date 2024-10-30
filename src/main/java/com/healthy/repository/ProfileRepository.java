package com.healthy.repository;

import com.healthy.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
