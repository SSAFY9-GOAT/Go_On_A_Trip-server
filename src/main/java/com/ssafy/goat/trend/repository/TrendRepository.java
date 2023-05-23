package com.ssafy.goat.trend.repository;

import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.trend.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrendRepository extends JpaRepository<Trend, Long>, TrendRepositoryCustom {
    Optional<Trend> findByHotPlace(HotPlace hotPlace);
}

