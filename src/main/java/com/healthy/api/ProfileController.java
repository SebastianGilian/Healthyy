package com.healthy.api;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("profiles")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(){
        List<ProfileDTO> profile = profileService.getAllProfiles();
        return ResponseEntity.ok(profile);
    }
}
