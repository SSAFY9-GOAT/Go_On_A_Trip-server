package com.ssafy.goat.tripplan;

import com.ssafy.goat.common.domain.TimeBaseEntity;
import com.ssafy.goat.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class TripPlan extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_plan_id")
    private Long id;
    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public TripPlan(Long id, Member member, String title) {
        this.id = id;
        this.title = title;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void changeTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TripPlan{" +
                "id=" + id +
                ", member=" + member +
                ", title='" + title + '\'' +
                '}';
    }
}
