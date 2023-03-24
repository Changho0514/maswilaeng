package com.maswilaeng.dto.follow.response;

import lombok.Getter;

@Getter
public class FollowResponseDto {
    private Long toUserId;
    private Long fromUserId;
    private int followerCount;

    public FollowResponseDto(Long toUserId, Long fromUserId, int followerCount) {
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.followerCount = followerCount;
    }
}
