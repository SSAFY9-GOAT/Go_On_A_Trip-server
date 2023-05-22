package com.ssafy.goat.hotplace;

import com.ssafy.goat.common.domain.TimeBaseEntity;
import com.ssafy.goat.common.domain.UploadFile;
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
public class HotPlace extends TimeBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "hot_place_id")
    private Long hotPlaceId;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(nullable = false)
    private String desc;
    @Column(nullable = false)
    private int hit;
    @Column(nullable = false)
    private int vote;
    @Column(nullable = false)
    private String visitedDazte;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "file_id")
    private UploadFile uploadFile;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public HotPlace(Long hotPlaceId, String name, String desc, int hit, int vote, String visitedDazte, UploadFile uploadFile, Member member) {
        this.hotPlaceId = hotPlaceId;
        this.name = name;
        this.desc = desc;
        this.hit = hit;
        this.vote = vote;
        this.visitedDazte = visitedDazte;
        this.uploadFile = uploadFile;
        this.member = member;
    }
}
