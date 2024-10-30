package com.healthy.service.impl;

import com.healthy.exception.BadRequestException;
import com.healthy.model.entity.User;
import com.healthy.repository.UserRepository;
import com.healthy.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service

public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("El email "+user.getEmail()+" ya esta registrado");
        }
        return userRepository.save(user);
    }
}