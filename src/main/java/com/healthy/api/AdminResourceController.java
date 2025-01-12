package com.healthy.api;

import com.healthy.dto.ResourceCreateUpdateDTO;
import com.healthy.dto.ResourceDetailsDTO;
import com.healthy.model.entity.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import java.util.List;
import com.healthy.service.AdminResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/resources")
public class AdminResourceController {
    private final AdminResourceService adminResourceService;

    @GetMapping
    public ResponseEntity<List<ResourceDetailsDTO>> getAllResources() {
        List<ResourceDetailsDTO> resources = adminResourceService.getAll();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ResourceDetailsDTO>> paginateResources(
            @PageableDefault(size=5, sort="title") Pageable pageable)
    {
        Page<ResourceDetailsDTO> resources = adminResourceService.paginate(pageable);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDetailsDTO> getResourceById(@PathVariable Integer id) {
        ResourceDetailsDTO resource = adminResourceService.findById(id);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResourceDetailsDTO> createResource(@Valid @RequestBody ResourceCreateUpdateDTO resourceFromDto) {
        ResourceDetailsDTO newResource = adminResourceService.create(resourceFromDto);
        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDetailsDTO> updateResource(@PathVariable Integer id,
                                                             @Valid @RequestBody ResourceCreateUpdateDTO resourceFromDto) {
        ResourceDetailsDTO updatedResource = adminResourceService.update(id, resourceFromDto);
        return new ResponseEntity<>(updatedResource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteResource(@PathVariable("id") Integer id) {
        adminResourceService.delete(id);
        return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
    }
}