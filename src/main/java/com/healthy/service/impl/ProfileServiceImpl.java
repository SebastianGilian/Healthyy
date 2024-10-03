package com.healthy.service.impl;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
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

    @Transactional
    @Override
    public ProfileDTO createProfile(ProfileCreateDTO profileDTO){
        Profile profile = profileMapper.toProfileCreateDTO(profileDTO);

        User user = userRepository.findById(profileDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUser(user);
        return profileMapper.toProfileDTO(profileRepository.save(profile));
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProfileDTO> getAllProfiles(){
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profileMapper::toProfileDTO)
                .toList();
    }
}
