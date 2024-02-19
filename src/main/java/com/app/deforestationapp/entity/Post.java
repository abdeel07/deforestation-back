package com.app.deforestationapp.entity;

import com.app.deforestationapp.entity.enums.PostType;
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
    private Integer nbLike;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
