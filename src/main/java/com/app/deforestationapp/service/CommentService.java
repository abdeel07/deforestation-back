package com.app.deforestationapp.service;

import com.app.deforestationapp.dto.CommentDTO;
import com.app.deforestationapp.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    Comment add(CommentDTO comment);

    List<Comment> getByPostId(Long postId);
}
