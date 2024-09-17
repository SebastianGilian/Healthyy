package com.healthy.api;

import com.healthy.model.entity.Profile;
import com.healthy.service.AdminProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/profiles")


public class AdminProfileController {
    private final AdminProfileService adminProfileService;

    @GetMapping
    public ResponseEntity<List<Profile>> list(){
        List<Profile> profiles = adminProfileService.getAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Profile>> paginate(
            @PageableDefault(size=5, sort= "gender")Pageable pageable){
        Page<Profile>  page = adminProfileService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable Integer id){
        Profile profile = adminProfileService.findById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile profile){
        Profile profileCreated = adminProfileService.create(profile);
        return new ResponseEntity<>(profileCreated, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(@PathVariable Integer id,
                                          @RequestBody Profile profile){
        Profile updatedProfile = adminProfileService.update(id, profile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> delete(@PathVariable Integer id){
        adminProfileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
