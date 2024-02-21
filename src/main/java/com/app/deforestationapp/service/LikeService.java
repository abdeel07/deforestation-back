package com.app.deforestationapp.service;

public interface LikeService {
    String addLike(Long userId, Long postId);

    String unlikePost(Long userId, Long postId);
}
