package com.ssafy.goat.member.service;

import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    void beforeEach() {
        memberRepository.save(Member.builder()
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
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        String loginId = "ssafy";
        String loginPw = "12345678";

        //when
        LoginMember loginMember = accountService.login(loginId, loginPw);

        //then
        assertThat(loginMember.getLoginId()).isEqualTo(loginId);
    }
}