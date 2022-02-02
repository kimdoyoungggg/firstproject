package com.example.firstproject.service;

import com.example.firstproject.dto.Articleform;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {

        // 예상
        Article a = new Article(1L, "test1" , "1111");
        Article b = new Article(2L, "test2" , "1111");
        Article c = new Article(3L, "test3" , "1111");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> article = articleService.index();

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_성공() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "test1", "1111");

        // 실제
         Article article = articleService.show(id);


        //비교
        assertEquals(expected.toString(), article.toString());

    }


    @Test
    void show_실패() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);


        //비교
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void create_성공() {
        // 예상
        String title ="라라";
        String content="44";
        Articleform dto = new Articleform(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);


        //비교
        assertEquals(expected.toString(), article.toString());
    }


    @Test
    @Transactional
    void create_실패() {
        // 예상
        String title ="라라";
        String content="44";
        Articleform dto = new Articleform(4L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);


        //비교
        assertEquals(expected, article);
    }
}