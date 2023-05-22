package com.ssafy.goat.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class HotPlaceListResponse {

    private Long hotPlaceId;
    private String name;
    private String desc;
    private int hit;
    private String storeFileName;

    private String nickname;
    private String createdDate;

    @Builder
    public HotPlaceListResponse(Long hotPlaceId, String name, String desc, int hit, String storeFileName, String nickname, LocalDateTime createdDate) {
        this.hotPlaceId = hotPlaceId;
        this.name = name;
        this.desc = desc;
        this.hit = hit;
        this.storeFileName = storeFileName;
        this.nickname = nickname;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
    }
}
