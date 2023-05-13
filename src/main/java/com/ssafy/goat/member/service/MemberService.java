package com.ssafy.goat.member.service;


import com.ssafy.goat.member.dto.MemberAddDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long signUp(MemberAddDto memberAddDto);
}
