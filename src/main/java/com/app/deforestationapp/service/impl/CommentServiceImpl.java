package com.app.deforestationapp.service.impl;

import com.app.deforestationapp.entity.Comment;
import com.app.deforestationapp.repository.CommentRepository;
import com.app.deforestationapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
