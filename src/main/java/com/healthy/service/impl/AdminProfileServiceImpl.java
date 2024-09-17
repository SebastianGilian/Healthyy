package com.healthy.service.impl;

import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.AdminProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProfileServiceImpl implements AdminProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Profile> paginate(Pageable pageable){
        return profileRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Profile create(Profile profile) {
        User user = userRepository.findById(profile.getUser().getId()).orElseThrow(()-> new RuntimeException("User not found with id: "+profile.getUser()));

        profile.setUser(user);

        return profileRepository.save(profile);
    }
    @Transactional(readOnly = true)
    @Override
    public Profile findById(Integer id){
        return profileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with id: "+id));
    }

    @Transactional
    @Override
    public Profile update(Integer id, Profile profile) {
        Profile profileFromDb = findById(id);
        profileFromDb.setUser(profile.getUser());
        profileFromDb.setAge(profile.getAge());
        profileFromDb.setGender(profile.getGender());
        profileFromDb.setHeight(profile.getHeight());
        profileFromDb.setWeight(profile.getWeight());
        profileFromDb.setHealthConditions(profile.getHealthConditions());

        return profileRepository.save(profileFromDb);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Profile not found with id: "+id));
        profileRepository.delete(profile);
    }
}
