package com.app.deforestationapp.service.impl;

import com.app.deforestationapp.dto.CommentDTO;
import com.app.deforestationapp.entity.Comment;
import com.app.deforestationapp.entity.Post;
import com.app.deforestationapp.entity.User;
import com.app.deforestationapp.repository.CommentRepository;
import com.app.deforestationapp.repository.PostRepository;
import com.app.deforestationapp.repository.UserRepository;
import com.app.deforestationapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment add(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId()).get();
        User user = userRepository.findById(commentDTO.getUserId()).get();

        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
