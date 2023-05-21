package com.ssafy.goat.tripplan.service;

import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface PlanService {
    @Transactional(readOnly = true)
    Page<PlanListDto> searchPlans(PlanSearch condition, Pageable pageable);
}
