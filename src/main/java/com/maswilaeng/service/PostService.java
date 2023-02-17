package com.maswilaeng.service;

import com.maswilaeng.domain.entity.Post;
import com.maswilaeng.domain.entity.User;
import com.maswilaeng.domain.repository.PostRepository;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.post.request.PostRequestDto;
import com.maswilaeng.dto.post.request.PostUpdateDto;
import com.maswilaeng.dto.post.response.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    /* CREATE */
    @Transactional
    public void save(Long userId, PostRequestDto PostRequestDto) {

        /* User 정보를 가져와 dto에 담아준다. */
        User user = userRepository.findById(userId).get();
        postRepository.save(PostRequestDto.toEntity(user));
    }

    /* READ 게시글 리스트 조회 */
    @Transactional(readOnly = true)
    public Post findPostById(Long postId) {
        return postRepository.findByPostId(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + postId));
    }

    /*
     * UPDATE -> dirty checking 으로 하는지?
     */
    @Transactional
    public void updatePost(Long userId, PostUpdateDto updateDto) throws Exception { // id 없는 객체 -> null "mergeX"

        Post toUpdatePost = postRepository.findById(updateDto.getPostId()).get();

        if (Objects.equals(toUpdatePost.getUser().getId(), userId)) {
            toUpdatePost.update(updateDto);
        } else{
            throw new Exception("해당 게시물 접근 권한 없음");
        }

    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        postRepository.delete(post);
    }

    public Page<Post> getUserPostList(Long userId, int currentPage) {
        return postRepository.findByUserId(userId, PageRequest.of(
                currentPage - 1, 20, Sort.Direction.DESC, "createdAt"));
    }


    public List<PostListResponseDto> getPostListDefault() {
        return postRepository.findAllFetchJoin(PageRequest.of(0,500));

    }
//
//    /* search */
//    @Transactional(readOnly = true)
//    public Page<Post> search(String keyword, Pageable pageable) {
//        Page<Post> postList = postRepository.findByTitleContaining(keyword, pageable);
//        return postList;
//    }
}
