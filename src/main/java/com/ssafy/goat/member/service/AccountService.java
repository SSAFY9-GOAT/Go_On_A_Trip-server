package com.ssafy.goat.member.service;

import com.ssafy.goat.member.dto.LoginMember;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountService {
    LoginMember login(String loginId, String loginPw);
}
