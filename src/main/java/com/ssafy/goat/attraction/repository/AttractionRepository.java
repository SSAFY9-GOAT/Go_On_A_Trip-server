package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<AttractionInfo, Integer>, AttractionRepositoryCustom {
}
