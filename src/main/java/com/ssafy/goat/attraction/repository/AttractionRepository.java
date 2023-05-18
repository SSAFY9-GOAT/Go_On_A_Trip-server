package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.repository.dto.AttractionSearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<AttractionInfo, Integer>, AttractionRepositoryCustom {
}
