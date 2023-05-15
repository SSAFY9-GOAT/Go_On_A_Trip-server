package com.ssafy.goat.article.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.article.QArticle.article;
import static com.ssafy.goat.member.QMember.member;


public class ArticleRepositoryImpl implements ArticleQueryRepository{
    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ArticleListDto> searchByCondition(ArticleSearch condition, Pageable pageable) {
        System.out.println("pageable = " + pageable.getOffset());
        System.out.println(pageable.getPageSize());
        List<ArticleListDto> articleListDto = queryFactory
                .select(Projections.constructor(ArticleListDto.class,
                        article.id,
                        article.title,
                        member.nickname,
                        article.hit,
                        article.createdDate
                        ))
                .from(article)
                .join(article.member, member)
                .where(isKeyword(condition.getCondition()))
                .orderBy(article.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count = queryFactory
                .select(article.id)
                .from(article)
                .fetch()
                .size();
        return new PageImpl<>(articleListDto, pageable, count);
    }
private BooleanExpression isKeyword(String keyword) {
    return StringUtils.hasText(keyword) ? article.title.like("%" + keyword + "%") : null;
}
}
