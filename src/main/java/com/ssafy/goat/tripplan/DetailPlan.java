package com.ssafy.goat.tripplan;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class DetailPlan extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "detail_plan_id")
    private Long id;
    @Column(nullable = false)
    private int sequence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_plan_id")
    private TripPlan tripPlan;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @Builder
    public DetailPlan(Long id, TripPlan tripPlan, AttractionInfo attractionInfo, int sequence) {
        this.id = id;
        this.sequence = sequence;
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
    }
}
