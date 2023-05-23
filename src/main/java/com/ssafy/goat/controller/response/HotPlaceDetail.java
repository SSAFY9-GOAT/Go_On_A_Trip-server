package com.ssafy.goat.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceDetail {
    private String name;
    private String desc;
    private double latitude;
    private double longitude;
    private String visitedDate;
    private String nickname;
    private String image;

    @Builder
    public HotPlaceDetail(String name, String desc, double latitude, double longitude, String visitedDate, String nickname, String image) {
        this.name = name;
        this.desc = desc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitedDate = visitedDate;
        this.nickname = nickname;
        this.image = image;
    }
}
