package com.maswilaeng.dto.post.response;

import com.maswilaeng.domain.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PostResponseDto {
    private Long post_id;
    private LocalDateTime createdAt;
    private String thumbnail;
    private String title;
    private String content;
    private LocalDateTime modifiedAt;


    /* Dto -> Entity */
    public PostResponseDto(Post post) {
        this.post_id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.thumbnail = post.getThumbnail();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.modifiedAt = post.getModifiedAt();
    }
}
