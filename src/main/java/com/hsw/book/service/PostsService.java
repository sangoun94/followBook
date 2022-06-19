package com.hsw.book.service;

import com.hsw.book.domain.posts.Posts;
import com.hsw.book.domain.posts.PostsRepository;
import com.hsw.book.web.dto.PostsRequestDto;
import com.hsw.book.web.dto.PostsResponseDto;
import com.hsw.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;


    @PersistenceContext
    EntityManager entityManager;

    public Long save(PostsRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다.")
        );

        post.update(requestDto.getTitle(), requestDto.getContent());
        System.out.println(entityManager.contains(post));
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id로 조회 결과가 없습니다")
        );

        return new PostsResponseDto(post);
    }
}
