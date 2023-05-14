package com.ssafy.goat.member.service;

import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void beforeEach() {
        savedMember = memberRepository.save(Member.builder()
                .loginId("ssafy")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("0101")
                .birthyear("1986")
                .gender("F")
                .nickname("광주5반")
                .nicknameLastModifiedDate(LocalDateTime.now())
                .authority(CLIENT)
                .snsId("-1")
                .build());
//        savedMember = memberRepository.save(member);
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
//        String loginId = "ssafy";
//        String loginPw = "12345678";

        //when
        Long joinId = memberService.signUp(MemberAddDto.builder()
                .loginId("ssafy1")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("0101")
                .birthyear("1986")
                .gender("F")
                .nickname("광주5반")
                .authority(CLIENT)
                .snsId("-1")
                .build());

        Optional<Member> joinUser = memberRepository.findByLoginId("ssafy1");

        //then
        assertThat(joinUser.get().getId()).isEqualTo(joinId);
    }
}