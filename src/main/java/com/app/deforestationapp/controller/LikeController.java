package com.app.deforestationapp.controller;

import com.app.deforestationapp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = { "http://localhost:3000" })
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Like a post
    @PostMapping("/like")
    public ResponseEntity<String> likePost(@RequestParam Long userId, @RequestParam Long postId) {
        String response = likeService.addLike(userId, postId);
        return ResponseEntity.ok(response);
    }

    // Unlike a post
    @DeleteMapping("/unlike")
    public ResponseEntity<String> unlikePost(@RequestParam Long userId, @RequestParam Long postId) {
        String response = likeService.unlikePost(userId, postId);
        return ResponseEntity.ok(response);
    }
}
