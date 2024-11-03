package com.infofy.infofy.controller;

import com.infofy.infofy.dto.ApiResponse;
import com.infofy.infofy.exception.ResourceNotFoundException;
import com.infofy.infofy.model.Post;
import com.infofy.infofy.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Post>>> getAllPost() {
        List<Post> postList = postService.getAllPost();
        ApiResponse<List<Post>> response = new ApiResponse<>(true, "Post List", postList);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Post>> savePost(@Valid @RequestBody Post post) {
        Post savedPost = postService.savePost(post);
        ApiResponse<Post> response = new ApiResponse<>(true, "Post Created", savedPost);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable Long id) {
        boolean isDeleted = postService.deletePostById(id);

        if (isDeleted) {
            ApiResponse<String> response = new ApiResponse<>(true, "Post Deleted", null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<String> response = new ApiResponse<>(false, "Post Not Found", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        ApiResponse<Post> response = new ApiResponse<>(true, "Post List", post);
        return ResponseEntity.ok(response);
    }
}
