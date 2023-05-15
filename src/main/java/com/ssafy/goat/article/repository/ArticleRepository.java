package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleQueryRepository{
    Optional<Article> findById(Long articleId);
}
