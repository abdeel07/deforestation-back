package com.app.deforestationapp.service.impl;

import com.app.deforestationapp.entity.Like;
import com.app.deforestationapp.entity.Post;
import com.app.deforestationapp.entity.User;
import com.app.deforestationapp.repository.LikeRepository;
import com.app.deforestationapp.repository.PostRepository;
import com.app.deforestationapp.repository.UserRepository;
import com.app.deforestationapp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String addLike(Long userId, Long postId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            return "User already liked this post.";
        } else {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found."));
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("Post not found."));

            Like like = new Like(null, user, post);
            likeRepository.save(like);

            post.setNbLike(post.getNbLike() + 1);
            postRepository.save(post);

            return "Post liked successfully.";
        }
    }

    @Override
    public String unlikePost(Long userId, Long postId) {
        Optional<Like> like = likeRepository.findByUserIdAndPostId(userId, postId);
        if (like.isPresent()) {
            likeRepository.delete(like.get());

            Post post = postRepository.findById(postId).get();
            post.setNbLike(post.getNbLike() - 1);
            postRepository.save(post);

            return "Like removed successfully.";
        } else {
            return "Like does not exist.";
        }
    }
}
