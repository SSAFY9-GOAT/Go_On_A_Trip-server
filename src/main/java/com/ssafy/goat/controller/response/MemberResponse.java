package com.ssafy.goat.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberResponse {
    private String loginId;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String birth;
    private String gender;

    @Builder
    public MemberResponse(String loginId, String name, String nickname, String email, String phone, String birth, String gender) {
        this.loginId = loginId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
    }
}
