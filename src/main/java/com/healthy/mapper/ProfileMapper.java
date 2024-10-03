package com.healthy.mapper;

import com.healthy.dto.GoalCreateDTO;
import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component

public class ProfileMapper {
    private final ModelMapper modelMapper;
    private final GoalMapper goalMapper;

    public ProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.goalMapper = new GoalMapper(modelMapper);
    }

    public Profile toProfileCreateDTO(ProfileCreateDTO profileCreateDTO) {
        Profile profile = modelMapper.map(profileCreateDTO, Profile.class);
        User user = new User();
        profile.setUser(user);

        profile.setGoals(profileCreateDTO.getGoals().stream()
                .map(goalMapper::toGoalCreateDTO)
                .toList());

        return profile;
    }
    public ProfileDTO toProfileDTO(Profile profile) {
        ProfileDTO profileDTO = modelMapper.map(profile, ProfileDTO.class);
        profileDTO.setName(profile.getName());
        profileDTO.setHeight(profile.getHeight());
        profileDTO.setWeight(profile.getWeight());
        profileDTO.setAge(profile.getAge());
        profileDTO.setGender(profile.getGender());
        profileDTO.setHealthConditions(profile.getHealthConditions());

        profileDTO.setGoals(profile.getGoals().stream()
                .map(goalMapper::toGoalDTO)
                .toList());
        return profileDTO;
    }
}
