package com.ssafy.goat.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceListRequest {
    private String name;
    private int sortCondition;

    @Builder
    public HotPlaceListRequest(String name, int sortCondition) {
        this.name = name;
        this.sortCondition = sortCondition;
    }
}
