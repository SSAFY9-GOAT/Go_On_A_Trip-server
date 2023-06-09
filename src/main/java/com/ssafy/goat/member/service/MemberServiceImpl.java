package com.ssafy.goat.member.service;

import com.ssafy.goat.common.exception.DuplicateException;
import com.ssafy.goat.controller.response.MemberResponse;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.SnsUser;
import com.ssafy.goat.member.dto.ChangUserDto;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.member.repository.SnsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final SnsUserRepository snsUserRepository;

    // TODO: 2023-05-16 아이디로 멤버 찾아오는거 함수로 빼기, 예외 처리하기
    @Override
    public Long signUp(MemberAddDto memberAddDto) {
        duplicateLoginId(memberAddDto);
        Member addMember = Member.builder()
                .loginId(memberAddDto.getLoginId())
                .loginPw(memberAddDto.getLoginPw())
                .username(memberAddDto.getUsername())
                .email(memberAddDto.getEmail())
                .phone(memberAddDto.getPhone())
                .birth(memberAddDto.getBirth())
                .birthyear(memberAddDto.getBirthyear())
                .gender(memberAddDto.getGender())
                .nickname(memberAddDto.getNickname())
                .nicknameLastModifiedDate(LocalDateTime.now())
                .authority(memberAddDto.getAuthority())
                .snsId(memberAddDto.getSnsId())
                .build();
        Member member = memberRepository.save(addMember);
        return member.getId();
    }

    @Override
    public String snsSignUp(Long memberId, String id) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            return null;
        }
        Member member = findMember.get();
        SnsUser saved = snsUserRepository.save(SnsUser.builder()
                .id(id)
                .member(member)
                .build());
        return saved.getId();
    }

    @Override
    public Member getSnsUser(String snsId) {
        Optional<SnsUser> findSnsUer = snsUserRepository.findById(snsId);
        if (!findSnsUer.isPresent()) {
            return null;
        }
        SnsUser snsUser = findSnsUer.get();
        return snsUser.getMember();
    }

    @Override
    public MemberResponse getUserInfo(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            return null;
        }
        Member member = findMember.get();
        return MemberResponse.builder()
                .loginId(member.getLoginId())
                .name(member.getUsername())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .birth(member.getBirthyear() + "년 " + member.getBirth().substring(0, 2) + "월 " + member.getBirth().substring(2) + "일")
                .gender(member.getGender().equals("M") ? "남성" : "여성")
                .build();
    }

    @Override
    public Long changeUserInfo(String LoginId, ChangUserDto changUserDto) {
        Optional<Member> findUser = memberRepository.findByLoginId(LoginId);
        if (!findUser.isPresent()) {
            return null;
        }
        Member member = findUser.get();

        if (!changUserDto.getPassword().equals(member.getLoginPw())) {
            return null;
        }

        if (!changUserDto.getEmail().equals(member.getEmail())) {
            member.changeEmail(changUserDto.getEmail());
        }
        if (!changUserDto.getNickname().equals(member.getNickname())) {
            member.changeNickname(changUserDto.getNickname());
        }
        if (!changUserDto.getPhone().equals(member.getPhone())) {
            member.changePhone(changUserDto.getPhone());
        }
        return member.getId();
    }

    @Override
    public Long changePassword(Long id, String oldPassword, String newPassword) {
        Optional<Member> findUser = memberRepository.findById(id);
        if (!findUser.isPresent()) {
            return null;
        }
        Member member = findUser.get();
        if (!member.getLoginPw().equals(oldPassword)) {
            return null;
        }
        member.changeLoginPw(newPassword);
        return member.getId();
    }

    @Override
    public int withdrawal(String id, String loginPw) {
        Optional<Member> findUser = memberRepository.findByLoginId(id);
        if (!findUser.isPresent()) {
            return -1;
        }
        Member member = findUser.get();
        if (!member.getLoginPw().equals(loginPw)) {
            return -1;
        }
        memberRepository.delete(member);
        return 1;
    }

    @Override
    public Long findAndChangePassword(String loginId, String newPassword) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            return null;
        }
        Member member = findMember.get();
        member.changeLoginPw(newPassword);
        return member.getId();
    }

    private void duplicateLoginId(MemberAddDto dto) {
        Optional<Member> loginId = memberRepository.findByLoginId(dto.getLoginId());
        if (loginId.isPresent()) {
            throw new DuplicateException();
        }
    }
}
