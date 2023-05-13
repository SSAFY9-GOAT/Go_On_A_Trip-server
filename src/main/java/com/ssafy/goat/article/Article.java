package com.ssafy.goat.article;

import com.ssafy.goat.common.domain.TimeBaseEntity;
import com.ssafy.goat.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


// TODO: 2023-05-12 access, PROTECTED 이게 뭘까 ?
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int hit;
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false)
    private Long memberId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Article(Long id, String title, String content, int hit, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long memberId, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.memberId = memberId;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void editArticle(String title, String content) {
        this.title = title;
        this.content = content;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void increaseHit() {
        this.hit += 1;
    }

//    public void changeMember(Long memberId) {
//        this.member = new Member(memberId);
//    }
}
