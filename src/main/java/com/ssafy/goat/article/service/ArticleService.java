package com.ssafy.goat.article.service;

import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ArticleService {
    @Transactional(readOnly = true)
    Page<ArticleListDto> searchArticles(ArticleSearch condition, Pageable pageable);

    ArticleDetailDto searchArticle(Long articleId);

    long addArticle (Long memberId, ArticleDto articleDto);

    long modifyArticle (Long memberId, ArticleDto articleDto);

    long deleteArticle (Long memberId, Long articleId);
    long increaseHit(Long articleId);

}
