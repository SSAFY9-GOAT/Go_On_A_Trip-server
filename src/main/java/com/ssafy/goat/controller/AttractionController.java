package com.ssafy.goat.controller;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.GugunService;
import com.ssafy.goat.attraction.service.SidoService;
import com.ssafy.goat.controller.response.AttractionResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attraction")
@RequiredArgsConstructor
@Slf4j
public class AttractionController {
    private final AttractionService attractionService;
    private final SidoService sidoService;
    private final GugunService gugunService;

    @GetMapping("/sido")
    @ApiOperation(value = "시도 정보")
    public ResponseEntity<?> sido() {
        return new ResponseEntity<>(sidoService.searchSidos(), HttpStatus.OK);
    }

    @GetMapping("/gugun/{sidoCode}")
    @ApiOperation(value = "구군 정보")
    public ResponseEntity<?> gugun(
            @PathVariable int sidoCode
    ) {
        return new ResponseEntity<>(gugunService.searchGuguns(sidoCode), HttpStatus.OK);
    }

//        @GetMapping("/search")
//    public List<AttractionResponse> search(
//            @RequestParam(name="sidoCode") int sidoCode,
//            @RequestParam(name="gugunCode") int gugunCode,
//            @RequestParam(name="contentTypeId") int contentTypeId
//    ) {
//            AttractionSearchCondition condition = AttractionSearchCondition.builder()
//                .sidoCode(sidoCode)
//                .gugunCode(gugunCode)
//                .build();
//
//        return attractionService.searchByCondition(condition);
//    }
    @GetMapping("/search")
    public List<AttractionResponse> searchAttraction(@RequestBody AttractionSearchCondition attractionSearchCondition) {
        return attractionService.searchByCondition(attractionSearchCondition);
    }
}
