package com.healthy.service.impl;


import com.healthy.dto.ProfileDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.SubPlanRepository;
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
}
