package com.ssafy.goat.article.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDetailDto {

    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    private Long memberId;
    private String nickname;

    @Builder
    public ArticleDetailDto(Long articleId, String title, String content, LocalDateTime createdDate, Long memberId, String nickname) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.memberId = memberId;
        this.nickname = nickname;
    }
}