package com.ssafy.goat.attraction.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.repository.AttractionRepositoryCustom;
import com.ssafy.goat.controller.response.AttractionResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.attraction.QAttractionInfo.attractionInfo;
import static com.ssafy.goat.attraction.QContentType.contentType;
import static com.ssafy.goat.attraction.QGugun.gugun;
import static com.ssafy.goat.attraction.QSido.sido;

@Repository
public class AttractionRepositoryImpl implements AttractionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AttractionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearch condition) {
        return queryFactory
                .select(Projections.constructor(AttractionResponse.class,
                        attractionInfo.id,
                        attractionInfo.title,
                        attractionInfo.addr1,
                        attractionInfo.zipcode,
                        attractionInfo.tel,
                        attractionInfo.firstImage,
                        attractionInfo.latitude,
                        attractionInfo.longitude))
                .from(attractionInfo)
                .where(attractionInfo.sido.code.eq(condition.getSidoCode()),
                        attractionInfo.gugun.code.eq(condition.getGugunCode()),
                        attractionInfo.contentType.id.eq(condition.getContentTypeId())
                )
                .fetch();
    }

}