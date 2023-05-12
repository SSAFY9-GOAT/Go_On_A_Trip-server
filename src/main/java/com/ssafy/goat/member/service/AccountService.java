package com.ssafy.goat.member.service;

import com.ssafy.goat.member.dto.LoginMember;

public interface AccountService {
    LoginMember login(String loginId, String loginPw);
}
