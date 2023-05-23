package com.ssafy.goat.notice.repository;

import com.ssafy.goat.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeQueryRepository {
    List<NoticeDto> searchTopNotions();
    Page<NoticeDto> searchNotions(Pageable pageable);
}
