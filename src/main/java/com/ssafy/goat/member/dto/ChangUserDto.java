package com.ssafy.goat.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ChangUserDto {
    private String password;
    private String email;
    private String phone;
    private String nickname;

    @Builder
    public ChangUserDto(String password, String email, String phone, String nickname) {
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
    }
}
