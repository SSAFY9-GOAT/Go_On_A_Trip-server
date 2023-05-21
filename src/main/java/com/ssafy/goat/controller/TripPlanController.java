package com.ssafy.goat.controller;

import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.service.PlanService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tripplan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
public class TripPlanController {
    private final PlanService planService;


    @GetMapping("")
    @ApiOperation(value = "여행계획 목록을 불러옴", response = PlanListDto.class)
    public Result<?> list(
            @RequestParam(name = "condition", defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        PlanSearch condition = PlanSearch.builder()
                .condition(keyword)
                .build();
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<PlanListDto> planListDto = planService.searchPlans(condition, pageRequest);
        return new Result<Page<PlanListDto>>(planListDto);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
