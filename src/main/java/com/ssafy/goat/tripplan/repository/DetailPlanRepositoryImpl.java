package com.ssafy.goat.tripplan.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.tripplan.dto.DetailPlanDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.attraction.QAttractionInfo.attractionInfo;
import static com.ssafy.goat.tripplan.QDetailPlan.detailPlan;
import static com.ssafy.goat.tripplan.QTripPlan.tripPlan;

public class DetailPlanRepositoryImpl implements DetailPlanQueryRepository{
    private final JPAQueryFactory queryFactory;
    public DetailPlanRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<DetailPlanDto> findByTripPlanId(Long tripPlanId) {
        List<DetailPlanDto> detailPlanDtos = queryFactory
                .select(Projections.constructor(DetailPlanDto.class,
                        attractionInfo.title,
                        detailPlan.id,
                        attractionInfo.latitude,
                        attractionInfo.longitude
                        ))
                .from(detailPlan)
                .join(detailPlan.tripPlan, tripPlan)
                .join(detailPlan.attractionInfo, attractionInfo)
                .where(tripPlan.id.eq(tripPlanId))
                .fetch();
        return detailPlanDtos;
    }
}
