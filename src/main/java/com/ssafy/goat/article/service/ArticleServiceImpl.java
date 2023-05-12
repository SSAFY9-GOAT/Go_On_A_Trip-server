package com.ssafy.goat.article.service;

import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.repository.ArticleRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{


    @Override
    public int addArticle(Long memberId, ArticleDto articleDto) {
        return 0;
    }

    @Override
    public ArticleDetailDto searchArticle(Long articleId) {
        return null;
    }

    @Override
    public List<ArticleListDto> searchArticles(ArticleSearch condition, int pageNum, int amount) {
        return null;
    }

    @Override
    public List<ArticleListDto> searchMyArticles(Long memberId, int pageNum, int amount) {
        return null;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public int editArticle(Long articleId, Long memberId, ArticleDto articleDto) {
        return 0;
    }

    @Override
    public int increaseHit(Long articleId) {
        return 0;
    }

    @Override
    public int removeArticle(Long articleId, Long memberId) {
        return 0;
    }
}
