package com.ssafy.goat.tripplan.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanListDto {
    private Long tripPlanId;
    private String title;
    private String nickname;
    private LocalDateTime createdDate;

    @Builder
    public PlanListDto(Long tripPlanId, String title, String nickname, LocalDateTime createdDate) {
        this.tripPlanId = tripPlanId;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }
}
