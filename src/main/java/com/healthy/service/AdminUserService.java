package com.healthy.service;
import com.healthy.model.entity.User;

public interface AdminUserService {
    User registerUser(User user);
    void deleteUser(Integer userId);


}