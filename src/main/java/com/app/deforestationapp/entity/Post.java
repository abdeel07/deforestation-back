package com.app.deforestationapp.entity;

import jakarta.persistence.*;
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

    private String heading;

    private String imageUrl;

    private String newsInput;

    private Integer nbLike;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
