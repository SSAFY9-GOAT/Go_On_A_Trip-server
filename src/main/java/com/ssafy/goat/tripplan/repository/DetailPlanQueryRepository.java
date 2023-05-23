package com.ssafy.goat.tripplan.repository;

import com.ssafy.goat.tripplan.dto.DetailPlanDto;

import java.util.List;

public interface DetailPlanQueryRepository {
    List<DetailPlanDto> findByTripPlanId (Long tripPlanId);
}
