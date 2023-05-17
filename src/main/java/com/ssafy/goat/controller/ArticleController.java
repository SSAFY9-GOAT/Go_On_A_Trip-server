package com.ssafy.goat.controller;

import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.service.ArticleService;
import com.ssafy.goat.controller.request.AddArticleRequest;
import com.ssafy.goat.member.dto.LoginMember;
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

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
public class ArticleController {
    private  final ArticleService articleService;

    @GetMapping("")
    @ApiOperation(value = "게시글 목록을 불러옴" , response = ArticleListDto.class)
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

    @PostMapping("/write")
    @ApiOperation(value = "게시글 등록")
    public ResponseEntity<?> write(
            @RequestBody AddArticleRequest request,
            @RequestParam(name = "loginUserId") long loginUserId
            ){
        ArticleDto articleDto = ArticleDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        long result = articleService.addArticle(loginUserId, articleDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value = "게시글 상세조회" , response = ArticleDetailDto.class)
    public Result<?> view(
            @PathVariable Long articleId
    ){
        articleService.increaseHit(articleId);
        ArticleDetailDto articleDetailDto = articleService.searchArticle(articleId);
        return new Result<ArticleDetailDto>(articleDetailDto);
    }

    @GetMapping("/{articleId}/modify")
    @ApiOperation(value = "게시글 수정 불러오기" , response = ArticleDetailDto.class)
    public Result<?> modify(
            @PathVariable Long articleId
    ){
        ArticleDetailDto articleDetailDto = articleService.searchArticle(articleId);
        return new Result<ArticleDetailDto>(articleDetailDto);
    }

    @PostMapping("/{articleId}/modify")
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<?> modify(
            @PathVariable long articleId,
            @RequestBody AddArticleRequest request,
            @RequestParam(name = "loginUserId") long loginUserId
    ) {
        ArticleDto articleDto = ArticleDto.builder()
                .id(articleId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        long result = articleService.modifyArticle(loginUserId, articleDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{articleId}/delete")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<?> delete(
            @PathVariable long articleId,
            @RequestParam(name = "loginUserId") long loginUserId
    ) {
        long result = articleService.deleteArticle(loginUserId, articleId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}