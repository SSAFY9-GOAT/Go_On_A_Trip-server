package com.ssafy.goat.tripplan.repository;

import com.ssafy.goat.tripplan.DetailPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailPlanRepository extends JpaRepository<DetailPlan, Long>, DetailPlanQueryRepository {
}
