package com.maswilaeng.dto.post.request;

import com.maswilaeng.Domain.entity.Post;
import com.maswilaeng.Domain.entity.User;
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
    private LocalDateTime created_at;
    private String thumbnail;
    private String title;
    private String content;
    private LocalDateTime modified_at;

    /* Dto -> Entity */
    public Post toEntity(User user) {
        Post posts = Post.builder()
                .user(user)
                .id(post_id)
                .created_at(created_at)
                .thumbnail(thumbnail)
                .title(title)
                .content(content)
                .modified_at(modified_at)
                .build();

        return posts;
    }

    @Builder
    public PostRequestDto(Long post_id, Long user_id, LocalDateTime created_at, String thumbnail, String title, String content, LocalDateTime modified_at) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.modified_at = modified_at;
    }
}
