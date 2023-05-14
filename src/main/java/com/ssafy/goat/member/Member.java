package com.ssafy.goat.member;

import com.ssafy.goat.common.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String loginPw;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String birth;
    @Column(nullable = false)
    private String birthyear;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private LocalDateTime nicknameLastModifiedDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;
    @Column(nullable = false)
    private String snsId;

    @Builder
    public Member(Long id, String loginId, String loginPw, String username, String email, String phone, String birth, String birthyear, String gender, String nickname, LocalDateTime nicknameLastModifiedDate, Authority authority, String snsId) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.birthyear = birthyear;
        this.gender = gender;
        this.nickname = nickname;
        this.nicknameLastModifiedDate = nicknameLastModifiedDate;
        this.authority = authority;
        this.snsId = snsId;
    }

    //== 비즈니스 로직 ==//
    public void changeLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
        this.nicknameLastModifiedDate = LocalDateTime.now();
    }
}
