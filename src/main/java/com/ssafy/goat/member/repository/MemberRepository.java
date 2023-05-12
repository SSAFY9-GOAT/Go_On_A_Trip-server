package com.ssafy.goat.member.repository;

import com.ssafy.goat.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginIdAndLoginPw(@Param("loginId") String loginId, @Param("loginPw") String loginPw);
}