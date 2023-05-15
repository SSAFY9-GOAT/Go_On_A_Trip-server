package com.ssafy.goat.article.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListDto {

    private Long articleId;
    private String title;
    private String nickname;
    private int hit;
    private LocalDateTime createdDate;

    public ArticleListDto(Long articleId, String title, String nickname, int hit, LocalDateTime createdDate) {
        this.articleId = articleId;
        this.title = title;
        this.nickname = nickname;
        this.hit = hit;
        this.createdDate = createdDate;
    }
}
