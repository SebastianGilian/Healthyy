package com.healthy.api;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.dto.ProfileUpdateDTO;
import com.healthy.model.entity.Profile;
import com.healthy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/profiles")
public class AdminProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAll(){
        List<ProfileDTO> profile = profileService.getAll();
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable("id") Integer id){
        ProfileDTO profile = profileService.findById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileCreateDTO> createProfile(@RequestBody ProfileCreateDTO profileCreateDTO) {
        profileService.createProfile(profileCreateDTO);
        return new ResponseEntity<>(profileCreateDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Integer userId,
            @RequestBody ProfileUpdateDTO profileUpdateDTO) {
        Profile updatedProfile = profileService.updateProfile(userId, profileUpdateDTO);
        return ResponseEntity.ok(updatedProfile);
    }

}
