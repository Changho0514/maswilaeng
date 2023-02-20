package com.maswilaeng.dto.post.request;

import com.maswilaeng.domain.entity.Post;
import com.maswilaeng.domain.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


// 데이터 전달 목적
@Data
@NoArgsConstructor // 인자 없이 객체 생성 가능
@Builder
public class PostRequestDto {

    private Long post_id;
    private Long user_id;
    private LocalDateTime createdAt;
    private String thumbnail;
    private String title;
    private String content;
    private LocalDateTime modifiedAt;

    /* Dto -> Entity */
    public Post toEntity(User user) {
        Post posts = Post.builder()
                .user(user)
                .id(post_id)
                .createdAt(createdAt)
                .thumbnail(thumbnail)
                .title(title)
                .content(content)
                .modifiedAt(modifiedAt)
                .build();

        return posts;
    }

    @Builder
    public PostRequestDto(Long post_id, Long user_id, LocalDateTime createdAt, String thumbnail, String title, String content, LocalDateTime modifiedAt) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.createdAt = createdAt;
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }
}
