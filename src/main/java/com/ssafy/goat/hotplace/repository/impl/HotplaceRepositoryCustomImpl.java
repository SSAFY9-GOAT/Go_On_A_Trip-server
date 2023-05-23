package com.ssafy.goat.hotplace.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.controller.request.HotPlaceListRequest;
import com.ssafy.goat.controller.response.HotPlaceDetail;
import com.ssafy.goat.controller.response.HotPlaceListResponse;
import com.ssafy.goat.hotplace.repository.HotplaceRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.hotplace.QHotPlace.*;
import static com.ssafy.goat.common.domain.QUploadFile.*;
import static com.ssafy.goat.member.QMember.member;

public class HotplaceRepositoryCustomImpl implements HotplaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HotplaceRepositoryCustomImpl(EntityManager em) {

        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<HotPlaceListResponse> searchByCondition(HotPlaceListRequest request) {
        List<HotPlaceListResponse> list = queryFactory
                .select(Projections.constructor(HotPlaceListResponse.class,
                        hotPlace.hotPlaceId,
                        hotPlace.name,
                        hotPlace.desc,
                        hotPlace.hit,
                        uploadFile.storeFileName,
                        member.nickname,
                        hotPlace.createdDate))
                .from(hotPlace)
                .join(hotPlace.member, member)
                .join(hotPlace.uploadFile, uploadFile)
                .where(hotPlace.name.like("%" + request.getName() + "%"))
                .orderBy(request.getSortCondition() == 1 ? hotPlace.createdDate.desc() : hotPlace.hit.desc())
                .fetch();
        return list;
    }

    @Override
    public HotPlaceDetail getHotplace(Long hotplaceId) {
        HotPlaceDetail hotPlaceDetail = queryFactory
                .select(Projections.constructor(HotPlaceDetail.class,
                        hotPlace.name,
                        hotPlace.desc,
                        hotPlace.latitude,
                        hotPlace.longitude,
                        hotPlace.visitedDate,
                        member.nickname,
                        uploadFile.storeFileName))
                .from(hotPlace)
                .join(hotPlace.member, member)
                .join(hotPlace.uploadFile, uploadFile)
                .where(hotPlace.hotPlaceId.eq(hotplaceId))
                .fetchOne();
        return hotPlaceDetail;
    }
}
