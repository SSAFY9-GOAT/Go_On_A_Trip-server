package com.ssafy.goat.member.repository;

import com.ssafy.goat.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw);

    Optional<Member> findByLoginId(@Param("loginId") String loginIn);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginIdAndPhone(String loginId, String phone);
}