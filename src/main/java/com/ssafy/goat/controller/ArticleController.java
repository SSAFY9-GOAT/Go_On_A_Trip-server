package com.ssafy.goat.controller;

import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class ArticleController {
    private  final ArticleService articleService;

    @GetMapping("")
    @ApiOperation(value = "게시글 목록을 불러옴", response = ArticleListDto.class)
    public Result<?> list(
            @RequestParam(name = "condition", defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") int sortCondition,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ArticleSearch condition = ArticleSearch.builder()
                .condition(keyword)
                .sortCondition(sortCondition)
                .build();
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<ArticleListDto> articleListDto = articleService.searchArticles(condition, pageRequest);
        return new Result<Page<ArticleListDto>>(articleListDto);
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}