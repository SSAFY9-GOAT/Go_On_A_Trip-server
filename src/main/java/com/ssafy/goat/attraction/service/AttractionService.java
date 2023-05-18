package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.goat.controller.response.AttractionResponse;

import java.util.List;

public interface AttractionService {
//    List<AttractionResponse> searchByCondition(AttractionSearch condition);
    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
}
