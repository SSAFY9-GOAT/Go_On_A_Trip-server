package com.ssafy.goat.trend.service.impl;

import com.ssafy.goat.controller.response.TrendViewDto;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.repository.HotplaceRepository;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.trend.Trend;
import com.ssafy.goat.trend.repository.TrendRepository;
import com.ssafy.goat.trend.service.TrendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrendServiceImpl implements TrendService {

    private final MemberRepository memberRepository;
    private final TrendRepository trendRepository;
    private final HotplaceRepository hotplaceRepository;

    @Override
    public int increaseInfo(Long memberId, Long hotPlaceId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            return -1;
        }
        Member member = findMember.get();
        int age = LocalDateTime.now().getYear() - Integer.parseInt(member.getBirthyear());
        log.debug("연령대 = {}", age);
        log.debug("지금 = {}", LocalDateTime.now().getYear());
        int gender = member.getGender().equals("M") ? 1 : 2;

        Optional<HotPlace> findHotplace = hotplaceRepository.findById(hotPlaceId);
        HotPlace hotPlace = findHotplace.get();

        Optional<Trend> findTrend = trendRepository.findByHotPlace(hotPlace);
        if (!findTrend.isPresent()) {
            trendRepository.save(Trend.builder()
                    .female(0)
                    .male(0)
                    .teenage(0)
                    .twenty(0)
                    .thirty(0)
                    .hotPlace(hotPlace)
                    .build());
            findTrend = trendRepository.findByHotPlace(hotPlace);
        }

        Trend trend = findTrend.get();

        if (gender == 1) {
            trend.increaseMale();
        } else {
            trend.increaseFemale();
        }

        switch (age / 10) {
            case 1:
                trend.increaseTeenage();
                break;
            case 2:
                trend.increaseTwenty();
                break;
            default:
                trend.increaseThirty();
                break;
        }

        return trend.getTwenty();
    }

    @Override
    public int increaseInfo(Long memberId, Long hotPlaceId, int size) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            return -1;
        }
        Member member = findMember.get();
        int age = LocalDateTime.now().getYear() - Integer.parseInt(member.getBirthyear());
        log.debug("연령대 = {}", age);
        log.debug("지금 = {}", LocalDateTime.now().getYear());
        int gender = member.getGender().equals("M") ? 1 : 2;

        Optional<HotPlace> findHotplace = hotplaceRepository.findById(hotPlaceId);
        HotPlace hotPlace = findHotplace.get();

        Optional<Trend> findTrend = trendRepository.findByHotPlace(hotPlace);
        if (!findTrend.isPresent()) {
            trendRepository.save(Trend.builder()
                    .female(0)
                    .male(0)
                    .teenage(0)
                    .twenty(0)
                    .thirty(0)
                    .hotPlace(hotPlace)
                    .build());
            findTrend = trendRepository.findByHotPlace(hotPlace);
        }

        Trend trend = findTrend.get();

        if (gender == 1) {
            trend.increaseMale(size);
        } else {
            trend.increaseFemale(size);
        }

        switch (age / 10) {
            case 1:
                trend.increaseTeenage(size);
                break;
            case 2:
                trend.increaseTwenty(size);
                break;
            default:
                trend.increaseThirty(size);
                break;
        }

        return trend.getTwenty();
    }

    @Override
    public TrendViewDto popularByTeenage() {
        return trendRepository.findPopularByTeenage();
    }

    @Override
    public TrendViewDto popularByTwenty() {
        return trendRepository.findPopularByTwenty();
    }

    @Override
    public TrendViewDto popularByThirty() {
        return trendRepository.findPopularByThirty();
    }

    @Override
    public TrendViewDto popularByMale() {
        return trendRepository.findPopularByMale();
    }

    @Override
    public TrendViewDto popularByFemale() {
        return trendRepository.findPopularByFemale();
    }
}
