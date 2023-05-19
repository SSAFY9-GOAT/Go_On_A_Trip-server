package com.ssafy.goat.member.service;

import com.ssafy.goat.member.dto.LoginMember;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountService {
    LoginMember login(String loginId, String loginPw);

    LoginMember getLoginMember(String loginId);

    void saveRefreshToken(String loginId, String refreshToken);

    Object getRefreshToken(String loginId);

    void deleteRefreshToken(String loginId);

    Boolean getUserPhone(String loginId, String phone);
}
