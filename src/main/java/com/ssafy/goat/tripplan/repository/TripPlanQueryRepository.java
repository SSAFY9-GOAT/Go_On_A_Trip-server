package com.ssafy.goat.tripplan.repository;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TripPlanQueryRepository {
    Page<PlanListDto> searchByCondition(PlanSearch condition, Pageable pageable);

    List<AttractionDto> searchByTitle(String title);
}
