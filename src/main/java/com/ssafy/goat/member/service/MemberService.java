package com.ssafy.goat.member.service;


import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long signUp(MemberAddDto memberAddDto);
}
