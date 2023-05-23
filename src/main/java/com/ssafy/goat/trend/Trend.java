package com.ssafy.goat.trend;

import com.ssafy.goat.hotplace.HotPlace;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trend {

    @Id
    @GeneratedValue
    @Column(name = "trend_id")
    private Long trendId;
    private int teenage;
    private int twenty;
    private int thirty;
    private int male;
    private int female;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public Trend(Long trendId, int teenage, int twenty, int thirty, int male, int female, HotPlace hotPlace) {
        this.trendId = trendId;
        this.teenage = teenage;
        this.twenty = twenty;
        this.thirty = thirty;
        this.male = male;
        this.female = female;
        this.hotPlace = hotPlace;
    }

    //== 비즈니스 로직 ==//
    public void increaseTeenage() {
        this.teenage += 1;
    }

    public void increaseTwenty() {
        this.twenty += 1;
    }

    public void increaseThirty() {
        this.thirty += 1;
    }

    public void increaseMale() {
        this.male += 1;
    }

    public void increaseFemale() {
        this.female += 1;
    }
}
