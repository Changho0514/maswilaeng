package com.maswilaeng.controller;

import com.maswilaeng.domain.entity.Follow;
import com.maswilaeng.domain.entity.User;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.common.ResponseDto;
import com.maswilaeng.dto.follow.request.FollowRequestDto;
import com.maswilaeng.dto.follow.response.FollowResponseDto;
import com.maswilaeng.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserRepository userRepository;

    /**
     * 팔로우
     */
    @PostMapping
    public ResponseEntity<?> follow(@RequestBody FollowRequestDto followRequestDto) throws Exception {
        FollowResponseDto follow = followService.createFollow(followRequestDto.getToUserId(), followRequestDto.getFromUserId());
        return ResponseEntity.ok(follow);
    }



    /**
     * 언팔로우
     */
    @DeleteMapping
    public ResponseEntity<?> unfollow(@RequestBody FollowRequestDto followRequestDto) throws Exception {
        followService.deleteFollow(followRequestDto.getToUserId(), followRequestDto.getFromUserId());
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK));
    }

    /**
     * 팔로우 여부 확인
     */
    @GetMapping("/isFollowing")
    public ResponseEntity<?> isFollowing(@RequestParam Long toUserId, @RequestParam Long fromUserId) throws Exception {
        return ResponseEntity.ok(followService.isFollowing(toUserId, fromUserId));
    }

    /**
     * 팔로잉 목록 조회 (fetch join)
     */
    @GetMapping("/followingList")
    public ResponseEntity<?> followingList(@RequestParam Long fromUserId) throws Exception {
        return ResponseEntity.ok().body(followService.getFollowingList(fromUserId));
    }

    /**
     * 팔로워 목록 조회(fetch join)
     */
    @GetMapping("/followerList")
    public ResponseEntity<?> followerList(@RequestParam Long toUserId) throws Exception {
        return ResponseEntity.ok().body(followService.getFollowerList(toUserId));
    }
}

