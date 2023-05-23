package com.ssafy.goat.notice.service;

import com.ssafy.goat.notice.dto.NoticeDto;
import com.ssafy.goat.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDto> searchTopNotions() {
        return noticeRepository.searchTopNotions();
    }

    @Override
    public Page<NoticeDto> searchNotions(Pageable pageable) {
        return noticeRepository.searchNotions(pageable);
    }
}
