package com.ssafy.goat.article;

import com.ssafy.goat.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// TODO: 2023-05-12 access, PROTECTED 이게 뭘까 ? 
public class Article {

    private Long id;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long memberId;

    private Member member;

    public Article(Long id) {
        this.id = id;
    }

    @Builder
    public Article(Long id, String title, String content, int hit, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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
