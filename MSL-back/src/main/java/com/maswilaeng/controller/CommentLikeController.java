package com.maswilaeng.controller;

import com.maswilaeng.dto.comment.request.CommentLikeRequestDto;
import com.maswilaeng.dto.common.ResponseDto;
import com.maswilaeng.service.CommentLikeService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentId}/comment-like")
    public ResponseEntity<?> insert(@RequestBody @Valid CommentLikeRequestDto commentLikeRequestDto) {
        commentLikeService.insertCommentLike(commentLikeRequestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK));
    }

    @DeleteMapping("/{commentId}/comment-like")
    public ResponseEntity<?> delete(@RequestBody @Valid CommentLikeRequestDto commentLikeRequestDto) {
        commentLikeService.deleteCommentLike(commentLikeRequestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK));
    }

    @PostMapping("/{commentId}/comment-hate")
    public ResponseEntity<?> insertHate(@RequestBody @Valid CommentLikeRequestDto commentLikeRequestDto) {
        commentLikeService.insertCommentHate(commentLikeRequestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK));
    }

    @DeleteMapping("/{commentId}/comment-hate")
    public ResponseEntity<?> deleteHate(@RequestBody @Valid CommentLikeRequestDto commentLikeRequestDto) {
        commentLikeService.deleteCommentHate(commentLikeRequestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK));
    }
}
