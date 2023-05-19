package com.ssafy.goat.member.service;

import com.ssafy.goat.common.exception.LoginException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ssafy.goat.common.exception.ExceptionMessage.LOGIN_EXCEPTION;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final MemberRepository memberRepository;

    @Override
    public LoginMember login(String loginId, String loginPw) {
        Optional<Member> findMember = memberRepository.findByLoginIdAndLoginPw(loginId, loginPw);
        if (!findMember.isPresent()) {
            throw new LoginException(LOGIN_EXCEPTION);
        }
        Member member = findMember.get();

        boolean snsUser = !member.getSnsId().equals("-1");

        return LoginMember.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .authority(member.getAuthority().toString())
                .snsUser(snsUser)
                .build();
    }

    @Override
    public LoginMember getLoginMember(String loginId) {
        Member member = getMember(loginId);

        boolean snsUser = !member.getSnsId().equals("-1");

        return LoginMember.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .authority(member.getAuthority().toString())
                .snsUser(snsUser)
                .build();
    }

    @Override
    public void saveRefreshToken(String loginId, String refreshToken) {
        Member member = getMember(loginId);
        member.changeToken(refreshToken);
    }

    @Override
    public Object getRefreshToken(String loginId) {
        Member member = getMember(loginId);
        return member.getToken();
    }


    @Override
    public void deleteRefreshToken(String loginId) {
        Member member = getMember(loginId);
        member.changeToken(null);
    }

    @Override
    public Boolean getUserPhone(String loginId, String phone) {
        log.debug("[비밀번호 찾기 - 서비스] 아이디 =" + loginId);
        log.debug("[비밀번호 찾기 - 서비스] 전화번호 =" + phone);
        Optional<Member> findMember = memberRepository.findByLoginIdAndPhone(loginId, phone);
        if (!findMember.isPresent()) {
            return false;
        }
        return true;
    }

    private Member getMember(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            throw new LoginException("");
        }
        return findMember.get();
    }
}
