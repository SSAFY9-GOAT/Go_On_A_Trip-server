package com.ssafy.goat.controller;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.controller.request.AddTripPlanRequest;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.dto.TripPlanDto;
import com.ssafy.goat.tripplan.service.PlanService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tripplan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
public class TripPlanController {
    private final PlanService planService;
    private final AttractionService attractionService;

    @GetMapping("")
    @ApiOperation(value = "여행계획 목록 조회", response = PlanListDto.class)
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

    @GetMapping("tripPlanList/{title}")
    @ApiOperation(value = "관광지정보 조회")
    public List<AttractionDto> search(
            @PathVariable String title
    ) {
        List<AttractionDto> attractions = planService.searchPlans(title);
        return attractions;
    }

    @PostMapping("/write")
    @ApiOperation(value = "여행계획 작성")
    public ResponseEntity<?> write(
            @RequestBody AddTripPlanRequest request,
            @RequestParam(name = "loginUserId") long loginUserId
    ) {
        Long tripPlanId = planService.addTripPlan(loginUserId, request.getTitle());
        List<Integer> idList = new ArrayList<>();

        for(String id: request.getTripPlanIdList()){
            idList.add(Integer.parseInt(id));
        }
        List<AttractionInfo> attractionInfos = attractionService.searchAttraction(idList);
        int num = 1;
        for(AttractionInfo info: attractionInfos){
            planService.addDetailPlan(loginUserId, tripPlanId, info.getId(), num++);
        }
        return new ResponseEntity<>(tripPlanId, HttpStatus.CREATED);
    }

    @GetMapping("/{tripPlanId}")
    @ApiOperation(value = "여행계획 상세조회")
    public ResponseEntity<?> view(
            @PathVariable Long tripPlanId
    ) {
        TripPlanDto tripPlanDto = planService.showPlan(tripPlanId);
        return new ResponseEntity<>(tripPlanDto, HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
