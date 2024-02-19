package com.app.deforestationapp.controller;

import com.app.deforestationapp.entity.Post;
import com.app.deforestationapp.entity.enums.PostType;
import com.app.deforestationapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = { "http://localhost:3000" })
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Get all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAll();
        return ResponseEntity.ok(posts);
    }

    // Get posts by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Post>> getByType(@PathVariable PostType type) {
        List<Post> posts = postService.getByType(type);
        return ResponseEntity.ok(posts);
    }

    // Add a new post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.add(post);
        return ResponseEntity.ok(newPost);
    }

    // Get a single post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        try {
            Post post = postService.get(id);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a post by ID
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        try {
            Post updatedPost = postService.update(id, postDetails);
            return ResponseEntity.ok(updatedPost);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
