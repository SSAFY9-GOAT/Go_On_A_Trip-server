package com.ssafy.goat.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class TrendViewDto {
    private String name;
    private String firstImage;
    private int hit;

    @Builder
    public TrendViewDto(String name, String firstImage, int hit) {
        this.name = name;
        this.firstImage = firstImage;
        this.hit = hit;
    }
}
