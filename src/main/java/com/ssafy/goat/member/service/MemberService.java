package com.ssafy.goat.member.service;


import com.ssafy.goat.controller.response.MemberResponse;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.ChangUserDto;
import com.ssafy.goat.member.dto.MemberAddDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long signUp(MemberAddDto memberAddDto);

    String snsSignUp(Long memberId, String id);

    Member getSnsUser(String snsId);

    MemberResponse getUserInfo(String loginId);

    Long changeUserInfo(String LoginId, ChangUserDto changUserDto);

    Long changePassword(Long id, String oldPassword, String newPassword);

    int withdrawal(String id, String loginPw);

    Long findAndChangePassword(String loginId, String newPassword);
}
