package com.ssafy.goat.trend.repository;

import com.ssafy.goat.controller.response.TrendViewDto;

public interface TrendRepositoryCustom {
    TrendViewDto findPopularByTeenage();

    TrendViewDto findPopularByTwenty();

    TrendViewDto findPopularByThirty();

    TrendViewDto findPopularByMale();

    TrendViewDto findPopularByFemale();
}
