package com.ssafy.goat.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddHotPlaceDto {
    private String name;
    private String desc;
    private double latitude;
    private double longitude;
    private String visitedDate;
    private String originalFilename;
    private String storeFileName;

    @Builder
    public AddHotPlaceDto(String name, String desc, double latitude, double longitude, String visitedDate, String originalFilename, String storeFileName) {
        this.name = name;
        this.desc = desc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitedDate = visitedDate;
        this.originalFilename = originalFilename;
        this.storeFileName = storeFileName;
    }
}
