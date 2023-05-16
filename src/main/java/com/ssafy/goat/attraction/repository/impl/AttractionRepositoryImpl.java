//package com.ssafy.goat.attraction.repository.impl;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.ssafy.goat.attraction.repository.AttractionRepositoryCustom;
//import com.ssafy.goat.attraction.repository.dto.AttractionSearchCondition;
//import com.ssafy.goat.controller.response.AttractionResponse;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//public class AttractionRepositoryImpl implements AttractionRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//    public AttractionRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition) {
//        return queryFactory
//                .select(Projections.fields(AttractionResponse.class))
//                .from(attractionInfo)
//                .where(
//                        attractionInfo.sido.code.eq(condition.getSidoCode()),
//                        attractionInfo.gugun.code.eq(condition.getGugunCode())
//                )
//                .fetch();
//    }
//}