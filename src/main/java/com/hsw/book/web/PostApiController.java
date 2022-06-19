package com.hsw.book.web;

import com.hsw.book.domain.posts.PostsRepository;
import com.hsw.book.service.PostsService;
import com.hsw.book.web.dto.PostsRequestDto;
import com.hsw.book.web.dto.PostsResponseDto;
import com.hsw.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsRequestDto postsRequestDto) {
        return postsService.save(postsRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        PostsResponseDto dto = postsService.findById(id);
        return dto;
    }
}
