package com.healthy.service.impl;


import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.SubPlanRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final SubPlanRepository subPlanRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ProfileDTO> getAll(){
        List<Profile> profiles = profileRepository.findAll();

        return profiles.stream()
                .map(profileMapper::toProfileDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public ProfileDTO findById(Integer id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile "+id+" not found"));
        return profileMapper.toProfileDTO(profile);
    }

    @Override
    public ProfileCreateDTO create(ProfileCreateDTO profileCreateDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User "+username+" not found"));

        Profile profile = profileMapper.toProfile(profileCreateDTO);
        profile.setUser(user);
        profileRepository.save(profile);
        return profileCreateDTO;
    }
}
