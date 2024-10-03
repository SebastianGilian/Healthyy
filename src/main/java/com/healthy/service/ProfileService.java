package com.healthy.service;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    List<ProfileDTO> getAllProfiles();
    ProfileDTO getProfileById(Integer id);
}
