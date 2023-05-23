package com.ssafy.goat.notice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private Boolean top;
    private LocalDateTime createdDate;

    @Builder
    public NoticeDto(Long id, String title, String content, Boolean top, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.top = top;
        this.createdDate = createdDate;
    }
}
