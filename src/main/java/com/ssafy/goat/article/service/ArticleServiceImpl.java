package com.ssafy.goat.article.service;

import com.ssafy.goat.article.Article;
import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.repository.ArticleRepository;
import com.ssafy.goat.common.exception.ArticleException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.ssafy.goat.common.exception.ExceptionMessage.ARTICLE_MEMBER_DISCREPANCY;
import static com.ssafy.goat.common.exception.ExceptionMessage.NOT_FOUND_ARTICLE;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<ArticleListDto> searchArticles(ArticleSearch condition, Pageable pageable) {
        return articleRepository.searchByCondition(condition, pageable);
    }

    @Override
    public ArticleDetailDto searchArticle(Long articleId) {
        ArticleDetailDto findArticle = articleRepository.searchById(articleId);
        if(findArticle == null){
            throw new NoSuchElementException();
        }
        return findArticle;
    }

    @Override
    public long addArticle(Long memberId, ArticleDto articleDto) {
        Member member = findMember(memberId);
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .member(member)
                .build();
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }

    @Override
    public long increaseHit(Long articleId) {
        Article article = findArticle(articleId);
        article.increaseHit();

        Article upArticle = articleRepository.save(article);
        return upArticle.getId();
    }

    private Member findMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }
        return findMember.get();
    }

    private Article findArticle(Long articleID) {
        Optional<Article> findArticle = articleRepository.findById(articleID);
        if(!findArticle.isPresent()) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }
        return findArticle.get();
    }
}
