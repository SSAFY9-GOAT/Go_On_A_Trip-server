package com.ssafy.goat.member.dto;

import com.ssafy.goat.member.Authority;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberAddDto {
    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String birth;
    private String birthyear;
    private String gender;
    private String nickname;
    private Authority authority;
    private String snsId;

    @Builder
    public MemberAddDto(String loginId, String loginPw, String username, String email, String phone, String birth, String birthyear, String gender, String nickname, Authority authority, String snsId) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.birthyear = birthyear;
        this.gender = gender;
        this.nickname = nickname;
        this.authority = authority;
        this.snsId = snsId;
    }
}
