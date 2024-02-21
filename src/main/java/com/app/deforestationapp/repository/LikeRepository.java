package com.app.deforestationapp.repository;

import com.app.deforestationapp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // Method to check if a like exists for a given user and post
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    // Method to find a like by user and post (useful for unlike functionality)
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}
