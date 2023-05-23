package com.ssafy.goat.notice.service;

import com.ssafy.goat.common.exception.NoticeException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.notice.Notice;
import com.ssafy.goat.notice.dto.NoticeDto;
import com.ssafy.goat.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<NoticeDto> searchTopNotices() {
        return noticeRepository.searchTopNotices();
    }

    @Override
    public Page<NoticeDto> searchNotices(Pageable pageable) {
        return noticeRepository.searchNotices(pageable);
    }

    @Override
    public Long addNotice(Long loginUserId, NoticeDto noticeDto) {
        Optional<Member> findMember = memberRepository.findById(loginUserId);
        if (!findMember.isPresent()) {
            throw new NoticeException();
        }
        Member member = findMember.get();

        Notice notice = Notice.builder()
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .top(noticeDto.getTop())
                .createdBy(member)
                .lastModifiedBy(member)
                .build();
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getId();
    }

    @Override
    public NoticeDto searchNotice(Long noticeId) {
        NoticeDto noticeDto = noticeRepository.searchNotice(noticeId);
        return noticeDto;
    }

    @Override
    public Long modifyNotice(Long loginUserId, NoticeDto noticeDto) {
        Optional<Notice> findNotice = noticeRepository.findById(noticeDto.getId());
        if(!findNotice.isPresent()) {
            throw new NoticeException();
        }
        Notice notice = findNotice.get();
        notice.editNotice(noticeDto.getTitle(), noticeDto.getContent());
        Notice editNotice = noticeRepository.save(notice);
        return editNotice.getId();
    }

    @Override
    public Long deleteNotice(Long loginUserId, Long noticeId) {
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        if(!findNotice.isPresent()) {
            throw new NoticeException();
        }
        Notice notice = findNotice.get();
        if(!notice.getCreatedBy().getId().equals(loginUserId)) {
            throw new NoticeException();
        }
        noticeRepository.deleteById(noticeId);
        return noticeId;
    }

}
