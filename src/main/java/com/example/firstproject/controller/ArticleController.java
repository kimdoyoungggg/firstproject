package com.example.firstproject.controller;

import com.example.firstproject.dto.Articleform;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public  String createArticle(Articleform form){

        // 1. Dto를 Entity로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "redi";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : " +id);

        // 1. id로 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터 모델 등록
        model.addAttribute("article" , articleEntity);

        // 3. 보여줄 페이지 설정
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model){

        // 1. 모든 Airtcle 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

        //2. 가져온 Airtcle 묶음을 view로 전달
        model.addAttribute("articleList",articleEntityList);
        return "articles/index";
    }

}
