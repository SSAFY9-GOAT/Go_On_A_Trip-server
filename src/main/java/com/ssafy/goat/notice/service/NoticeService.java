package com.ssafy.goat.notice.service;


import com.ssafy.goat.notice.Notice;
import com.ssafy.goat.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NoticeService {
    List<NoticeDto> searchTopNotices();

    Page<NoticeDto> searchNotices(Pageable pageable);

    Long addNotice(Long loginUserId, NoticeDto noticeDto);

    NoticeDto searchNotice(Long noticeId);
}
