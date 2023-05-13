package com.ssafy.goat.notice.service;

import com.ssafy.goat.notice.dto.AddNoticeDto;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeService {
    @Transactional
    Long writeNotice(String loginId, AddNoticeDto addNoticeDto);
}
