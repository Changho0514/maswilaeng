package com.maswilaeng.service;

import com.maswilaeng.domain.entity.Post;
import com.maswilaeng.domain.repository.CommentRepository;
import com.maswilaeng.domain.repository.PostRepository;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.post.response.PostResponseDto;
import com.maswilaeng.dto.search.request.SearchRequestDto;
import com.maswilaeng.dto.search.response.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<PostResponseDto> findByWriter(SearchRequestDto dto) {
            return postRepository.findByNickName(dto.getContent()).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findByTitle(SearchRequestDto dto) {
        return postRepository.findByTitle(dto.getContent()).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findByTitleContent(SearchRequestDto dto) {
        return postRepository.findByTitleContent(dto.getContent()).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findByCommentWriter(SearchRequestDto dto) {
        return commentRepository.findByNickName(dto.getContent()).stream()
                .map(comment -> comment.getPost())
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findByCommentContent(SearchRequestDto dto) {
        return commentRepository.findByContent(dto.getContent()).stream()
                .map(comment -> comment.getPost())
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

}
