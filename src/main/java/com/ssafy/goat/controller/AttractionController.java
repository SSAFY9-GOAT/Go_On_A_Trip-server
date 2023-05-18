package com.ssafy.goat.controller;

import com.ssafy.goat.attraction.dto.SidoDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.SidoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/attraction")
@RequiredArgsConstructor
@Slf4j
public class AttractionController {
    private final SidoService sidoService;

    @GetMapping("/sido")
    @ApiOperation(value = "시도 정보")
    public ResponseEntity<?> sido() {
            return new ResponseEntity<>(sidoService.searchSidos(),HttpStatus.OK);
    }

//    @GetMapping("/gugun")
//    @ApiOperation(value = "구군 정보")
}
