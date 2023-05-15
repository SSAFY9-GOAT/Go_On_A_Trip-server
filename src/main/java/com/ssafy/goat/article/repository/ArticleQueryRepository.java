package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleQueryRepository {
    Page<ArticleListDto> searchByCondition(ArticleSearch condition, Pageable pageable);


}
