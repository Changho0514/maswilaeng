package com.maswilaeng.dto.post.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {

    private Long postId;

    private String nickname;

    private String userImage;

    private String thumbnail;

    private String title;

    private LocalDateTime createdAt;
//
//    private int likes;
//
//    private int comments;


    public PostListResponseDto(Long postId, String nickname, String userImage, String thumbnail, String title, LocalDateTime createdAt) {
        this.postId = postId;
        this.nickname = nickname;
        this.userImage = userImage;
        this.thumbnail = thumbnail;
        this.title = title;
        this.createdAt = createdAt;
    }
}
