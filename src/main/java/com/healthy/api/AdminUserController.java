package com.healthy.api;


import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import com.healthy.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")


public class AdminUserController {
    private final AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<List<User>> listAll(){
        List<User> users = adminUserService.listAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<User>> paginate(
            @PageableDefault(size=5, sort="username")Pageable pageable){
        Page<User> page = adminUserService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        User user = adminUserService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    //----------
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User userCreated = adminUserService.create(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id,
                                          @RequestBody User user){
        User updatedUser = adminUserService.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id){
        adminUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
