package com.healthy.service;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.dto.ProfileUpdateDTO;
import com.healthy.model.entity.Profile;

import java.util.List;

public interface ProfileService {

    List<ProfileDTO> getAll();
    ProfileDTO findById(Integer id);
    Profile createProfile(ProfileCreateDTO profileCreateDTO);
    Profile updateProfile(Integer userId, ProfileUpdateDTO profileUpdateDTO);
}
