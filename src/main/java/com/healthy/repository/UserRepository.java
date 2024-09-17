package com.healthy.repository;

import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
