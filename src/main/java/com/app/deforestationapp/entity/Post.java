package com.app.deforestationapp.entity;

import com.app.deforestationapp.entity.enums.PostType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String newsInput;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @Column(nullable = false)
    @Min(0)
    private Integer nbLike = 0;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference("post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("postLike")
    private List<Like> likes;
}
