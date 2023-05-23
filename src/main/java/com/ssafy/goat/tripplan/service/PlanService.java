package com.ssafy.goat.tripplan.service;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.dto.TripPlanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlanService {
    @Transactional(readOnly = true)
    Page<PlanListDto> searchPlans(PlanSearch condition, Pageable pageable);
    List<AttractionDto> searchPlans(String title);
    Long addTripPlan(Long loginMemberId, String title);
    Long addDetailPlan(Long loginMemberId, Long tripPlanId, int contentId, int num);
    TripPlanDto showPlan(Long tripPlanId);
    void removeDetailPlan(Long detailPlanId);
    void removeTripPlan(Long tripPlanId);
}
