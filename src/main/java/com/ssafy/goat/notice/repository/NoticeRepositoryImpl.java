package com.ssafy.goat.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.goat.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.goat.notice.QNotice.notice;

public class NoticeRepositoryImpl implements NoticeQueryRepository{
    private final JPAQueryFactory queryFactory;
    public NoticeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<NoticeDto> searchTopNotions() {
        List<NoticeDto> noticeDtos = queryFactory
                .select(Projections.constructor(NoticeDto.class,
                        notice.id,
                        notice.title,
                        notice.content,
                        notice.top,
                        notice.createdDate
                ))
                .from(notice)
                .where(notice.top.eq(true))
                .fetch();
        return noticeDtos;
    }

    @Override
    public Page<NoticeDto> searchNotions(Pageable pageable) {
        List<NoticeDto> noticeDtos = queryFactory
                .select(Projections.constructor(NoticeDto.class,
                        notice.id,
                        notice.title,
                        notice.content,
                        notice.top,
                        notice.createdDate
                ))
                .from(notice)
                .where(notice.top.eq(false))
                .orderBy(notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count = queryFactory
                .select(notice.id)
                .from(notice)
                .fetch()
                .size();
        return new PageImpl<>(noticeDtos, pageable, count);
    }
}
