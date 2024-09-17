package com.healthy.service.impl;

import com.healthy.model.entity.User;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service

public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;


    @Transactional(readOnly = true)
    public List<User> listAll(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<User> paginate(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public User create(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: "+id));

    }

    @Transactional
    @Override
    public User update(Integer id, User user){
        User userFromDb = findById(id);
        userFromDb.setEmail(user.getEmail());
        userFromDb.setRole(user.getRole());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setUsername(user.getUsername());

        return userRepository.save(userFromDb);
    }
    @Transactional
    @Override
    public void delete(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Profile not found with id: "+id));
        userRepository.delete(user);
    }
}
