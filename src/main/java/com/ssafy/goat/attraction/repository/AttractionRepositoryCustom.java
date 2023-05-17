package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.goat.controller.response.AttractionResponse;

import java.util.List;

public interface AttractionRepositoryCustom {

    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
}
