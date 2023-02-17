package com.maswilaeng.dto.post.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateDto {

    private Long postId;

    private String thumbnail;

    private String title;

    @NotNull
    private String content;
}
