package com.osckorea.board.controller;

import com.osckorea.board.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.osckorea.board.entity.Article;
import com.osckorea.board.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j // 로깅을 위한 어노테이션 log를 사용할 수있다.
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다 자동 연결.(의존성 주입)
    private ArticleRepository articleRepository;

    @GetMapping(value = "/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping(value = "/articles/posts")
    public String createArticle(ArticleDto form) {
        // 실무에서 println 안씀 로그를 쓴다(서버에서 일어나는 일을 기록하는것)
        log.info(form.toString());
        System.out.println(form.toString());

        // 1. Dto를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        // System.out.println(form.toString());
        // Article 이라는 엔티티 클래스를 사용
        // article 객체에 Dto인 form의 변환 메소드 toEntity() 를 넣음
        
        // 2. Repository에게 Entity를 DB에 저장하게 한다.
         Article saved = articleRepository.save(article);
        log.info(saved.toString());
         //  System.out.println(form.toString());
        return "";
    }

    @GetMapping(value = "/articles/{id}")
    public String read(@PathVariable Long id, Model model) {
        log.info("id :" + id);

        // 1. id로 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 만약 해당 아이디가 없다면 null을 반환해라 .orElse(null)
        // java8 부터 Optional<>로 감싸도돼.

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 선택

        return "articles/show";
    }

    @GetMapping(value = "/articles")
    public String getAll(Model model) {
        // 1. 모든 Articles을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 리스트를 뷰로 전달할 때는 모델을 슴 뷰로 전달한다.
        model.addAttribute("articleList",articleEntityList);

        // 3. 뷰 페이지를 설정
        return "articles/index";
    }


}
