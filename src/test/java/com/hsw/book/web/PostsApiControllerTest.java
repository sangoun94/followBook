package com.hsw.book.web;

import com.hsw.book.domain.posts.Posts;
import com.hsw.book.domain.posts.PostsRepository;
import com.hsw.book.service.PostsService;
import com.hsw.book.web.dto.PostsRequestDto;
import com.hsw.book.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsService service;

    @Autowired
    private PostsRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void Posts_등록() {
        //given
        String title = "title";
        String content = "content";
        PostsRequestDto dto = PostsRequestDto.builder()
                .title(title)
                .content(content)
                .author("HSW")
                .build();

        String url = "https://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = repository.findAll();
        Posts post = posts.get(0);

        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getAuthor()).isEqualTo("HSW");
    }

    @Test
    public void Posts_수정() {
        //given
        Posts post = repository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        Long id = post.getId();
        String updateTitle = "title2";
        String updateContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/" + id;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = repository.findAll();

        assertThat(posts.get(0).getContent()).isEqualTo(updateContent);
        assertThat(posts.get(0).getTitle()).isEqualTo(updateTitle);
    }
}
