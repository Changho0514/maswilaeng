package com.maswilaeng.service;

import com.maswilaeng.domain.entity.Follow;
import com.maswilaeng.domain.entity.User;
import com.maswilaeng.domain.repository.FollowRepository;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.follow.response.FollowingListResponseDto;
import com.maswilaeng.dto.follow.response.FollowResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    /** 팔로우 기능 */
    public FollowResponseDto createFollow(Long toUserId, Long fromUserId) throws Exception {

//        팔로우하고 있는지 확인 (팔로우 리스트 조인해서)
        if (!isFollowing(toUserId, fromUserId)) {
            //팔로우 하고 있지 않을 경우
            User toUser = userRepository.findById(toUserId).orElseThrow(
                    () -> new UsernameNotFoundException("toUser를 찾을 수 없습니다")
            );
            User fromUser = userRepository.findById(fromUserId).orElseThrow(
                    () -> new UsernameNotFoundException("fromUser를 찾을 수 없습니다")
            );
            Follow follow = new Follow(toUser, fromUser);
            followRepository.save(follow);
        }
        else{
            throw new Exception("이미 팔로우를 눌렀습니다");
        }
        int followerCount = userRepository.findById(toUserId).get().getFollowerList().size();
        return new FollowResponseDto(toUserId, fromUserId, followerCount);
    }


    /**
     * 팔로우 삭제
     */
    public void deleteFollow(Long toUserId, Long fromUserId) {
        if (isFollowing(toUserId, fromUserId)) {
            followRepository.deleteByToUserIdAndFromUserId(toUserId, fromUserId);
        }
    }

    /**
     * 팔로우 여부 확인
     */
    public boolean isFollowing(Long toUserId, Long fromUserId) {
        return followRepository.existsByToUser_IdAndFromUser_Id(toUserId, fromUserId);
    }

    /**
     * 사용자가 팔로우 하는 목록 조회(fetch join)
     */
    public List<FollowingListResponseDto> getFollowingList(Long fromUserId) {
        List<Follow> followingList = followRepository.findJoinToUsersByFromUserId(fromUserId);
        return  followingList.stream()
                .map(f -> f.getToUser())
                .map(FollowingListResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 사용자를 팔로우 하는 목록 조회(fetch join)
     */
    public List<FollowingListResponseDto> getFollowerList(Long toUserId) {
        List<Follow> followerList = followRepository.findJoinFromUsersByToUserId(toUserId);
        return followerList.stream()
                .map(f -> f.getFromUser())
                .map(FollowingListResponseDto::new)
                .collect(Collectors.toList());
    }

}
