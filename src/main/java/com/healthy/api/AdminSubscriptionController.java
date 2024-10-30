package com.healthy.api;

import com.healthy.dto.SubscriptionCreateDTO;
import com.healthy.dto.SubscriptionDTO;
import com.healthy.service.AdminSubscriptionService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/subscriptions")
public class AdminSubscriptionController {
    private final AdminSubscriptionService adminSubscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getAll() {
        List<SubscriptionDTO> subscription = adminSubscriptionService.getAll();
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SubscriptionDTO>> paginate(
            @PageableDefault(size=5) Pageable pageable)
    {
        Page<SubscriptionDTO> subscription = adminSubscriptionService.paginate(pageable);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getById(@PathVariable Integer id) {
        SubscriptionDTO subscription = adminSubscriptionService.findById(id);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> create(@Valid @RequestBody SubscriptionCreateDTO subscriptionFromDto) {
        SubscriptionDTO newSubscription = adminSubscriptionService.create(subscriptionFromDto);
        return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> update(@PathVariable Integer id,
                                                         @Valid @RequestBody SubscriptionCreateDTO subscriptionFromDto) {
        SubscriptionDTO updatedSubscription = adminSubscriptionService.update(id, subscriptionFromDto);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> delete(@PathVariable("id") Integer id) {
        adminSubscriptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}