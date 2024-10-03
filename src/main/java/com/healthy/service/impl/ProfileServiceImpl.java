package com.healthy.service.impl;


import com.healthy.dto.ProfileDTO;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ProfileDTO> getAllProfiles(){
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profileMapper::toProfileDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public ProfileDTO getProfileById(Integer id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return profileMapper.toProfileDTO(profile);
    }
}
