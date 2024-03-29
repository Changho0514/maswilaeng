package com.maswilaeng.service;

import com.maswilaeng.domain.entity.HashTag;
import com.maswilaeng.domain.entity.Post;
import com.maswilaeng.domain.entity.User;
import com.maswilaeng.domain.repository.PostRepository;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.post.request.PostRequestDto;
import com.maswilaeng.dto.post.request.PostUpdateDto;
import com.maswilaeng.dto.post.response.PostResponseDto;
import com.maswilaeng.dto.user.response.UserInfoResponseDto;
import com.maswilaeng.utils.SecurityUtil;
import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashTagService hashTagService;

    public void savePost(PostRequestDto postRequestDto) {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(
                () -> new RuntimeException("로그인 한 회원 정보가 존재하지 않습니다.")
        );
        Post post = postRequestDto.toEntity(user);

        Set<HashTag> hashTags = hashTagService.saveHashTags(postRequestDto.getHashTagSet(), post);
        post.setHashTagSet(hashTags);

        postRepository.save(post);
    }


    public PostResponseDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new EntityNotFoundException("해당 게시글이 존재하지 않습니다. id: " + postId));
        post.increaseHits();
        // 로그인 하지 않은 사용자 체크
        Class<? extends Authentication> aClass = SecurityContextHolder.getContext().getAuthentication().getClass();
        if(aClass.equals(AnonymousAuthenticationToken.class)) {
            return new PostResponseDto(post);
        } else {
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return new PostResponseDto(post, currentUserId);
        }

    }


    public void updatePost(PostUpdateDto updateDto) throws Exception { // id 없는 객체 -> null "mergeX"

        Long currentUserId = SecurityUtil.getCurrentUserId();

        Post toUpdatePost = postRepository.findById(updateDto.getPostId()).get();

        if (!currentUserId.equals(toUpdatePost.getUser().getId())) {
            throw new Exception("해당 게시물 접근 권한 없음");
        }

        Set<String> hashTagSet = updateDto.getHashTagSet();
        Set<HashTag> resultHashTags = hashTagService.updateHashTag(hashTagSet, toUpdatePost);

        toUpdatePost.setHashTagSet(resultHashTags);
        toUpdatePost.update(updateDto);
    }

    /* DELETE */
    public void deletePost(Long postId) throws ValidationException {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        Long postUserId = post.getUser().getId();
        Long currentUserId = SecurityUtil.getCurrentUserId();

        if (!currentUserId.equals(postUserId)) {
            throw new ValidationException("권한이 없는 사용자 입니다.");
        }

        Set<String> toDeleteHashTag = post.getHashTagSet().stream()
                .map(hashTag -> hashTag.getTag().getTagName())
                .collect(Collectors.toSet());
        hashTagService.deleteHashTags(toDeleteHashTag, post);
        postRepository.delete(post);
    }

    /* search */
    @Transactional(readOnly = true)
    public List<Post> searchAll() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    public List<PostResponseDto> findPostListByUserId(Long userId) {
        List<Post> postsByUserId = postRepository.findPostsByUserId(userId);
        return postsByUserId.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
