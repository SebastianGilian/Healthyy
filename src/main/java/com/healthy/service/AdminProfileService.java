package com.healthy.service;

import com.healthy.model.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminProfileService {
    List<Profile> getAll();
    Page<Profile> paginate(Pageable pageable);
    Profile create(Profile profile);
    Profile findById(Integer id);
    Profile update(Integer id, Profile updateProfile);
    void delete(Integer id);
}
