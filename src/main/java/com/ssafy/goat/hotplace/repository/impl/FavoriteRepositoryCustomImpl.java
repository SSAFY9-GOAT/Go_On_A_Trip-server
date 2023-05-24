package com.ssafy.goat.hotplace.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.hotplace.repository.FavoriteRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.hotplace.QFavorite.favorite;
import static com.ssafy.goat.hotplace.QHotPlace.hotPlace;
import static com.ssafy.goat.member.QMember.member;

public class FavoriteRepositoryCustomImpl implements FavoriteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public FavoriteRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Long> getUserLikeList(Long memberId) {
        List<Long> list = queryFactory.select(Projections.constructor(Long.class,
                        hotPlace.hotPlaceId))
                .from(favorite)
                .join(favorite.hotPlace, hotPlace)
                .join(favorite.member, member)
                .where(favorite.member.id.eq(memberId))
                .fetch();
        return list;
    }
}
