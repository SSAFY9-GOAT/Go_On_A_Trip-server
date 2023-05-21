package com.ssafy.goat.tripplan.service;

import com.ssafy.goat.tripplan.TripPlan;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.repository.TripPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    private final TripPlanRepository tripPlanRepository;
    @Override
    public Page<PlanListDto> searchPlans(PlanSearch condition, Pageable pageable) {
        return tripPlanRepository.searchByCondition(condition, pageable);
    }
}
