package com.ssafy.goat.member.repository;

import com.ssafy.goat.member.SnsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsUserRepository extends JpaRepository<SnsUser, String> {
}
