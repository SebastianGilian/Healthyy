package com.healthy.api;

import com.healthy.dto.ProfileDTO;
import com.healthy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/profiles")
@PreAuthorize("hasRole('USER')")
public class AdminProfileController {
    private final ProfileService profileService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ProfileDTO>> getAll(){
        List<ProfileDTO> profile = profileService.getAll();
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ProfileDTO> findById(@PathVariable("id") Integer id){
        ProfileDTO profile = profileService.findById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
