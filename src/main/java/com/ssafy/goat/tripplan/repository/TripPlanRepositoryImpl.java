package com.ssafy.goat.tripplan.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.w3c.dom.Attr;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.attraction.QAttractionInfo.attractionInfo;
import static com.ssafy.goat.attraction.QContentType.contentType;
import static com.ssafy.goat.member.QMember.member;
import static com.ssafy.goat.tripplan.QTripPlan.tripPlan;

public class TripPlanRepositoryImpl implements TripPlanQueryRepository {
    private final JPAQueryFactory queryFactory;

    public TripPlanRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<PlanListDto> searchByCondition(PlanSearch condition, Pageable pageable) {
        List<PlanListDto> planListDto = queryFactory
                .select(Projections.constructor(PlanListDto.class,
                        tripPlan.id,
                        tripPlan.title,
                        member.nickname,
                        tripPlan.createdDate
                ))
                .from(tripPlan)
                .join(tripPlan.member, member)
                .where(isKeyword(condition.getCondition()))
                .orderBy(tripPlan.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count = queryFactory
                .select(tripPlan.id)
                .from(tripPlan)
                .fetch()
                .size();
        return new PageImpl<>(planListDto, pageable, count);
    }

    @Override
    public List<AttractionDto> searchByTitle(String title) {
        return queryFactory
                .select(Projections.constructor(AttractionDto.class,
                        attractionInfo.id,
                        contentType.id,
                        attractionInfo.title,
                        attractionInfo.addr1,
                        attractionInfo.zipcode,
                        attractionInfo.firstImage,
                        attractionInfo.latitude,
                        attractionInfo.longitude
                        ))
                .from(attractionInfo)
                .join(attractionInfo.contentType, contentType)
                .where(isKeywordTitle(title))
                .fetch();
    }


    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? tripPlan.title.like("%" + keyword + "%") : null;
    }
    private BooleanExpression isKeywordTitle(String keyword) {
        return StringUtils.hasText(keyword) ? attractionInfo.title.like("%" + keyword + "%") : null;
    }
}