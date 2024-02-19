package com.app.deforestationapp.repository;

import com.app.deforestationapp.entity.Post;
import com.app.deforestationapp.entity.enums.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByType(PostType type);
}
