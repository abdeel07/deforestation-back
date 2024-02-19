package com.app.deforestationapp.service.impl;

import com.app.deforestationapp.entity.Post;
import com.app.deforestationapp.entity.enums.PostType;
import com.app.deforestationapp.repository.PostRepository;
import com.app.deforestationapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post add(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post get(Long id) throws Exception {
        return postRepository.findById(id).orElseThrow(
                () -> new Exception("Post not found for this id : " + id));
    }

    @Override
    public List<Post> getByType(PostType type) {
        return postRepository.findByType(type);
    }

    @Override
    public Post update(Long id, Post postDetails) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("Post not found for this id : " + id));

        post.setHeading(postDetails.getHeading());
        post.setImageUrl(postDetails.getImageUrl());
        post.setNewsInput(postDetails.getNewsInput());
        post.setNbLike(postDetails.getNbLike());

        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("Post not found for this id : " + id));

        postRepository.delete(post);
    }
}
