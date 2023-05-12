package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
