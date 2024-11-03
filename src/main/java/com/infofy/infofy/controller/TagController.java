package com.infofy.infofy.controller;

import com.infofy.infofy.dto.ApiResponse;
import com.infofy.infofy.exception.ResourceNotFoundException;
import com.infofy.infofy.model.Tag;
import com.infofy.infofy.service.TagService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Tag>>> gatAllTags() {
        logger.info("Getting all tags");
        List<Tag> tags = tagService.getAllTags();

        ApiResponse<List<Tag>> response = new ApiResponse<>(true, "Tag List", tags);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Tag>> saveTag(@Valid @RequestBody Tag tag) {
        Tag savedTag = tagService.saveTag(tag);
        ApiResponse<Tag> response = new ApiResponse<>(true, "Tag created", savedTag);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tag>> getTagByid(@PathVariable Long id) {
        Tag tag = tagService.getTagById(id).orElseThrow(() ->new ResourceNotFoundException("Tag not found by id "+ id));
        ApiResponse<Tag> response = new ApiResponse<>(false, "Tag fetched", tag);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTagById(@PathVariable Long id) {
        tagService.deleteTag(id);
        ApiResponse<String> response = new ApiResponse<>(true, "tag deleted", null);
        return ResponseEntity.ok(response);

    }
}
