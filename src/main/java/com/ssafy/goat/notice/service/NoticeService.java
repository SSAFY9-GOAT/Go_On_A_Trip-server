package com.ssafy.goat.notice.service;


import com.ssafy.goat.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NoticeService {
    List<NoticeDto> searchTopNotions();
    Page<NoticeDto> searchNotions(Pageable pageable);
}
