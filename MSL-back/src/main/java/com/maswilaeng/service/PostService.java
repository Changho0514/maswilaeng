package com.maswilaeng.service;

import com.maswilaeng.domain.entity.Post;
import com.maswilaeng.domain.entity.User;
import com.maswilaeng.domain.repository.PostRepository;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.post.request.PostRequestDto;
import com.maswilaeng.dto.post.request.PostUpdateDto;
import com.maswilaeng.utils.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void savePost(PostRequestDto postRequestDto) {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(
                () -> new RuntimeException("로그인 한 회원 정보가 존재하지 않습니다.")
        );
        postRepository.save(postRequestDto.toEntity(user));
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new EntityNotFoundException("해당 게시글이 존재하지 않습니다. id: " + postId));
    }


    @Transactional
    public void updatePost(Long userId, PostUpdateDto updateDto) throws Exception { // id 없는 객체 -> null "mergeX"

        Post toUpdatePost = postRepository.findById(updateDto.getId()).get();

        if (Objects.equals(toUpdatePost.getUser().getId(), userId)) {
            toUpdatePost.update(updateDto);
        } else{
            throw new Exception("해당 게시물 접근 권한 없음");
        }

    }

    /* DELETE */
    @Transactional
    public void delete(Long postId) throws ValidationException {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        Long postUserId = post.getUser().getId();
        Long currentUserId = SecurityUtil.getCurrentUserId();

        if (!currentUserId.equals(postUserId)) {
            throw new ValidationException("권한이 없는 사용자 입니다.");
        } else {
            postRepository.delete(post);
        }

    }

//    public UserPostListResponseDto getUserPostList(Long userId) {
//        return new UserPostListResponseDto(postRepository.findPostByUserId(userId));
//    }

    /* search */
    @Transactional(readOnly = true)
    public List<Post> searchAll() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }
}
