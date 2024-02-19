package com.app.deforestationapp.service;

import com.app.deforestationapp.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAll();

    Post add(Post post);

    Post get(Long id) throws Exception;

    Post update(Long id, Post post) throws Exception;

    void delete(Long id) throws Exception;
}
