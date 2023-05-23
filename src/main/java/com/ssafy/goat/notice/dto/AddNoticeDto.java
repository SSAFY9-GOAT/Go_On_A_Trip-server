package com.ssafy.goat.notice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddNoticeDto {
    private String title;
    private String content;
    private boolean top;

    @Builder
    public AddNoticeDto(String title, String content, boolean top) {
        this.title = title;
        this.content = content;
        this.top = top;
    }
}
