package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        {
            Long articleId = 4L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(4L, "your hobby","1111" );
            Comment a = new Comment(1L, article, "dy1", "sports");
            Comment b = new Comment(2L, article, "dy2", "reading book");
            Comment c = new Comment(3L, article, "dy3", "eat");
            List <Comment> expected = Arrays.asList(a,b,c);
            assertEquals(expected.toString() ,comments.toString());
        }
    }

    @Test
    void findByNickname() {
    }
}