package com.ssafy.goat.notice;

import com.ssafy.goat.common.domain.TimeBaseEntity;
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
public class Notice extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int hit;
    @Column(nullable = false)
    private boolean top;

    // TODO: 2023/05/13 이거 맞나?!
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member createdBy;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member lastModifiedBy;

    @Builder
    public Notice(Long id, String title, String content, int hit, boolean top, Member createdBy, Member lastModifiedBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.top = top;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }
}
