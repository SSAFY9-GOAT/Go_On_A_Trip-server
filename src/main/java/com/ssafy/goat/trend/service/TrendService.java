package com.ssafy.goat.trend.service;

import com.ssafy.goat.controller.response.TrendViewDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrendService {
    int increaseInfo(Long memberId, Long hotPlaceId);
    int increaseInfo(Long memberId, Long hotPlaceId,int size);

    TrendViewDto popularByTeenage();

    TrendViewDto popularByTwenty();

    TrendViewDto popularByThirty();

    TrendViewDto popularByMale();

    TrendViewDto popularByFemale();
}
