package com.maswilaeng.dto.post.response;

import com.maswilaeng.domain.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PostResponseDto {
    private Long post_id;
    private LocalDateTime created_at;
    private String thumbnail;
    private String title;
    private String content;
    private LocalDateTime modified_at;


    /* Dto -> Entity */
    public PostResponseDto(Post post) {
        this.post_id = post.getId();
        this.created_at = post.getCreated_at();
        this.thumbnail = post.getThumbnail();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.modified_at = post.getModified_at();
    }
}
