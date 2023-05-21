package com.ssafy.goat.tripplan.repository;

import com.ssafy.goat.tripplan.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long>, TripPlanQueryRepository {
}
