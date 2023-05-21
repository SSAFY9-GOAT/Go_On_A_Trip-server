package com.ssafy.goat.tripplan.repository;

import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripPlanQueryRepository {
    Page<PlanListDto> searchByCondition(PlanSearch condition, Pageable pageable);
}
