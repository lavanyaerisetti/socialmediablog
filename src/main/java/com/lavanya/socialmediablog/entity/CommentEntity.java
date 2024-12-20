package com.lavanya.socialmediablog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String userName;
    private String email;
    private String body;

    //ManyToOne Relationship - multiple comments belongs to single post

    @ManyToOne
    @JoinColumn(name="post_id",nullable = false)
    private PostEntity postEntity;
}
