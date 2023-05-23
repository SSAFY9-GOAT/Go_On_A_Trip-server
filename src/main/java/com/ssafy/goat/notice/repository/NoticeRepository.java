package com.ssafy.goat.notice.repository;

import com.ssafy.goat.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeQueryRepository {
}
