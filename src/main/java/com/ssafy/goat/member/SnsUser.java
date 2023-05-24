package com.ssafy.goat.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsUser {

    @Id
    @Column(name = "id")
    private String id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder

    public SnsUser(String id, Member member) {
        this.id = id;
        this.member = member;
    }
}
