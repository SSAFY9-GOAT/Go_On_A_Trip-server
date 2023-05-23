package com.ssafy.goat.trend.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.controller.response.TrendViewDto;
import com.ssafy.goat.trend.repository.TrendRepositoryCustom;

import javax.persistence.EntityManager;

import static com.ssafy.goat.common.domain.QUploadFile.uploadFile;
import static com.ssafy.goat.hotplace.QHotPlace.hotPlace;
import static com.ssafy.goat.trend.QTrend.trend;

public class TrendRepositoryCustomImpl implements TrendRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TrendRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public TrendViewDto findPopularByTeenage() {
        return queryFactory.select(Projections.constructor(TrendViewDto.class,
                        hotPlace.name,
                        uploadFile.storeFileName,
                        hotPlace.hit))
                .from(trend)
                .join(trend.hotPlace, hotPlace)
                .join(trend.hotPlace.uploadFile, uploadFile)
                .orderBy(trend.teenage.desc())
                .fetchFirst();
    }

    @Override
    public TrendViewDto findPopularByTwenty() {
        return queryFactory.select(Projections.constructor(TrendViewDto.class,
                        hotPlace.name,
                        uploadFile.storeFileName,
                        hotPlace.hit))
                .from(trend)
                .join(trend.hotPlace, hotPlace)
                .join(trend.hotPlace.uploadFile, uploadFile)
                .orderBy(trend.twenty.desc())
                .fetchFirst();
    }

    @Override
    public TrendViewDto findPopularByThirty() {
        return queryFactory.select(Projections.constructor(TrendViewDto.class,
                        hotPlace.name,
                        uploadFile.storeFileName,
                        hotPlace.hit))
                .from(trend)
                .join(trend.hotPlace, hotPlace)
                .join(trend.hotPlace.uploadFile, uploadFile)
                .orderBy(trend.thirty.desc())
                .fetchFirst();
    }

    @Override
    public TrendViewDto findPopularByMale() {
        return queryFactory.select(Projections.constructor(TrendViewDto.class,
                        hotPlace.name,
                        uploadFile.storeFileName,
                        hotPlace.hit))
                .from(trend)
                .join(trend.hotPlace, hotPlace)
                .join(trend.hotPlace.uploadFile, uploadFile)
                .orderBy(trend.male.desc())
                .fetchFirst();
    }

    @Override
    public TrendViewDto findPopularByFemale() {
        return queryFactory.select(Projections.constructor(TrendViewDto.class,
                        hotPlace.name,
                        uploadFile.storeFileName,
                        hotPlace.hit))
                .from(trend)
                .join(trend.hotPlace, hotPlace)
                .join(trend.hotPlace.uploadFile, uploadFile)
                .orderBy(trend.female.desc())
                .fetchFirst();
    }
}
