package com.ssafy.goat.controller;

import com.ssafy.goat.notice.dto.NoticeDto;
import com.ssafy.goat.notice.service.NoticeService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("")
    @ApiOperation(value = "공지사항 목록을 불러옴")
    public Result<?> list(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        List<NoticeDto> topNotions = noticeService.searchTopNotions();
        Page<NoticeDto> notions = noticeService.searchNotions(pageRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("topNotions", topNotions);
        result.put("notions", notions);
        return new Result<Map<String, Object>>(result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
