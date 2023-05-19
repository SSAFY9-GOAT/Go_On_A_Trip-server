package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.repository.AttractionRepository;
import com.ssafy.goat.controller.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
    public final AttractionRepository attractionRepository;

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearch condition) {
        return attractionRepository.searchByCondition(condition);

    }
}
