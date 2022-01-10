package com.example.firstproject.api;

import com.example.firstproject.dto.Articleform;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController // RestAPI용 컨트롤러는 html을 반환하는 것이 아닌 데이터(JSON)을 반환한다.
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

//    @Autowired
//    private ArticleRepository articleRepository;
//
      //GET
//    @GetMapping("/api/articles")
//    public List<Article> index(){
//        return articleRepository.findAll();
//    }

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

// Service 사용 전
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }

    // Service 사용 후
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }


// Service 사용 전
     //POST
//    @PostMapping("/api/articles")
//    public ResponseEntity<Article> create(@RequestBody Articleform dto){
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }

// Service 사용 후
    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody Articleform dto){
        Article created = articleService.create(dto);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

// Service 사용 전
      //PETCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id,
//                                         @RequestBody Articleform dto){
//
//        Article article = dto.toEntity();
//        log.info("id : {} , article {}", id, article.toString());
//
//        Article target = articleRepository.findById(id).orElse(null);
//        if (target == null || id != article.getId()){
//            log.info("잘못된 요청! id : {} , article {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }

// Service 사용 후
    //PETCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody Articleform dto){
        Article updated = articleService.update(id, dto);
        return (updated != null ) ?
        ResponseEntity.status(HttpStatus.OK).body(updated) :
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }




//
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        Article target = articleRepository.findById(id).orElse(null);
//        if (target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }


    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
    Article deleted = articleService.delete(id);
    return (deleted !=null ) ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //트렌잭션
    @PostMapping("/api/transcation-test")
    public ResponseEntity<List<Article>> transcationTest(@RequestBody List<Articleform> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
