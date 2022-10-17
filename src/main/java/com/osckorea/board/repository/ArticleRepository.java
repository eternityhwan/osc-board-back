package com.osckorea.board.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osckorea.board.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    // <관리대상엔티티, 대표값의 타입(Article의 대표값 id의 타입은 long)> 입력


    @Override
    List<Article> findAll();
}
