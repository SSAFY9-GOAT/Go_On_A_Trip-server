package com.ssafy.goat.controller;

import com.ssafy.goat.notice.dto.AddNoticeDto;
import com.ssafy.goat.notice.dto.NoticeDto;
import com.ssafy.goat.notice.service.NoticeService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("")
    @ApiOperation(value = "공지사항 목록을 불러옴")
    public Result<?> list(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        List<NoticeDto> topNotions = noticeService.searchTopNotices();
        Page<NoticeDto> notions = noticeService.searchNotices(pageRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("topNotions", topNotions);
        result.put("notions", notions);
        return new Result<Map<String, Object>>(result);
    }

    @PostMapping("/write")
    @ApiOperation(value = "공지사항 등록")
    public ResponseEntity<?> write(
            @RequestBody AddNoticeDto request,
            @RequestParam(name = "loginUserId") long loginUserId
    ) {
        NoticeDto noticeDto = NoticeDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .top(request.isTop())
                .build();
        long result = noticeService.addNotice(loginUserId, noticeDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{noticeId}")
    @ApiOperation(value = "공지사항 상세조회")
    public Result<?> view(
            @PathVariable Long noticeId
    ) {
        NoticeDto noticeDto = noticeService.searchNotice(noticeId);
        return new Result<>(noticeDto);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
