package com.healthy.service.impl;


import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.dto.ProfileUpdateDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.SubPlanRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.ProfileService;
import lombok.RequiredArgsConstructor;
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
    public Profile createProfile(ProfileCreateDTO profileCreateDTO) {
        User user = userRepository.findById(profileCreateDTO.getUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElse(new Profile());

        profile.setUser(user);
        profile.setUserName(profileCreateDTO.getUserName());
        profile.setAge(profileCreateDTO.getAge());
        profile.setHeight(profileCreateDTO.getHeight());
        profile.setWeight(profileCreateDTO.getWeight());
        profile.setGender(profileCreateDTO.getGender());
        profile.setHealthConditions(profileCreateDTO.getHealthConditions());


        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Integer userId, ProfileUpdateDTO profileUpdateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado para este usuario"));

        if (profileUpdateDTO.getUserName() != null) {
            profile.setUserName(profileUpdateDTO.getUserName());
        }
        if (profileUpdateDTO.getAge() != null) {
            profile.setAge(profileUpdateDTO.getAge());
        }
        if (profileUpdateDTO.getHeight() != null) {
            profile.setHeight(profileUpdateDTO.getHeight());
        }
        if (profileUpdateDTO.getWeight() != null) {
            profile.setWeight(profileUpdateDTO.getWeight());
        }
        if (profileUpdateDTO.getGender() != null) {
            profile.setGender(profileUpdateDTO.getGender());
        }
        if (profileUpdateDTO.getHealthConditions() != null) {
            profile.setHealthConditions(profileUpdateDTO.getHealthConditions());
        }

        return profileRepository.save(profile);
    }



}
