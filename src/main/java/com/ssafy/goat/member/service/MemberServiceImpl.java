package com.ssafy.goat.member.service;

import com.ssafy.goat.common.exception.DuplicateException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

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

    private void duplicateLoginId(MemberAddDto dto) {
        Optional<Member> loginId = memberRepository.findByLoginId(dto.getLoginId());
        if (loginId.isPresent()) {
            throw new DuplicateException();
        }
    }
}
