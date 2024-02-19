package com.app.deforestationapp.controller;

import com.app.deforestationapp.entity.Comment;
import com.app.deforestationapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = { "http://localhost:3000" })
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAll();
        return ResponseEntity.ok(comments);
    }

    // Add new comment
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.add(comment);
        return ResponseEntity.ok(createdComment);
    }

    // Get comments by post ID
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}
