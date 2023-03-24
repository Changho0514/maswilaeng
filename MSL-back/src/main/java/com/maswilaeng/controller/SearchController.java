package com.maswilaeng.controller;

import com.maswilaeng.dto.post.response.PostResponseDto;
import com.maswilaeng.dto.search.request.SearchRequestDto;
import com.maswilaeng.dto.search.response.SearchResponseDto;
import com.maswilaeng.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/writer")
    public ResponseEntity<?> findByWriter(@RequestBody SearchRequestDto dto) {
        return ResponseEntity.ok(searchService.findByWriter(dto));
    }

    @PostMapping("/title")
    public ResponseEntity<?> findByTitle(@RequestBody SearchRequestDto dto) {
        return ResponseEntity.ok(searchService.findByTitle(dto));
    }

    @PostMapping("/titleContent")
    public ResponseEntity<?> findByTitleContent(@RequestBody SearchRequestDto dto) {
        return ResponseEntity.ok(searchService.findByTitleContent(dto));
    }

    @PostMapping("/commentWriter")
    public ResponseEntity<?> findByCommentWriter(@RequestBody SearchRequestDto dto) {
        return ResponseEntity.ok(searchService.findByCommentWriter(dto));
    }

    @PostMapping("/commentContent")
    public ResponseEntity<?> findByCommentContent(@RequestBody SearchRequestDto dto) {
        return ResponseEntity.ok(searchService.findByCommentContent(dto));
    }
}
