package com.ssafy.goat.hotplace;

import com.ssafy.goat.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @GeneratedValue
    @Column(name = "favorite_id")
    private Long favoriteId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public Favorite(Long favoriteId, Member member, HotPlace hotPlace) {
        this.favoriteId = favoriteId;
        this.member = member;
        this.hotPlace = hotPlace;
    }
}
