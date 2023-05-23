package com.ssafy.goat.tripplan.service;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.repository.AttractionRepository;
import com.ssafy.goat.common.exception.ArticleException;
import com.ssafy.goat.common.exception.PlanException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.tripplan.DetailPlan;
import com.ssafy.goat.tripplan.TripPlan;
import com.ssafy.goat.tripplan.dto.DetailPlanDto;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.dto.TripPlanDto;
import com.ssafy.goat.tripplan.repository.DetailPlanRepository;
import com.ssafy.goat.tripplan.repository.TripPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ssafy.goat.common.exception.ExceptionMessage.ARTICLE_MEMBER_DISCREPANCY;
import static com.ssafy.goat.common.exception.ExceptionMessage.TRIPPLAN_EXCEPTION;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    private final TripPlanRepository tripPlanRepository;
    private final DetailPlanRepository detailPlanRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;
    @Override
    public Page<PlanListDto> searchPlans(PlanSearch condition, Pageable pageable) {
        return tripPlanRepository.searchByCondition(condition, pageable);
    }

    @Override
    public List<AttractionDto> searchPlans(String title) {
        return tripPlanRepository.searchByTitle(title);
    }

    @Override
    public Long addTripPlan(Long loginMemberId, String title) {
        Member member = findMember(loginMemberId);
        TripPlan tripPlan = TripPlan.builder()
                .title(title)
                .member(member)
                .build();
        TripPlan savedTripPlan = tripPlanRepository.save(tripPlan);
        return savedTripPlan.getId();
    }

    @Override
    public Long addDetailPlan(Long loginMemberId, Long tripPlanId, int contentId, int num) {
//        Member member = findMember(loginMemberId);
        TripPlan tripPlan = findTripPlan(tripPlanId);
        AttractionInfo attractionInfo = findAttractionInfo(contentId);
        DetailPlan detailPlan = DetailPlan.builder()
                .sequence(num)
                .tripPlan(tripPlan)
                .attractionInfo(attractionInfo)
                .build();
        DetailPlan savedDetailPlan = detailPlanRepository.save(detailPlan);
        return savedDetailPlan.getId();
    }

    @Override
    public TripPlanDto showPlan(Long tripPlanId) {
        Optional<TripPlan> findTripPlan = tripPlanRepository.findById(tripPlanId);
        if(!findTripPlan.isPresent()){
            throw new PlanException();
        }
        TripPlan tripPlan = findTripPlan.get();

        List<DetailPlanDto> detailPlans = detailPlanRepository.findByTripPlanId(tripPlanId);

        return TripPlanDto.builder()
                .tripPlanId(tripPlan.getId())
                .title(tripPlan.getTitle())
                .detailPlans(detailPlans)
                .nickname(tripPlan.getMember().getNickname())
                .build();
    }

    @Override
    public void removeDetailPlan(Long detailPlanId) {
        detailPlanRepository.deleteById(detailPlanId);
    }

    @Override
    public void removeTripPlan(Long tripPlanId) {
        tripPlanRepository.deleteById(tripPlanId);
    }

    private Member findMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }
        return findMember.get();
    }

    private TripPlan findTripPlan(Long tripPlanId) {
        Optional<TripPlan> findTripPlan = tripPlanRepository.findById(tripPlanId);
        if(!findTripPlan.isPresent()) {
            throw new ArticleException(TRIPPLAN_EXCEPTION);
        }
        return findTripPlan.get();
    }

    private AttractionInfo findAttractionInfo(int contentId) {
        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(contentId);
        if(!findAttractionInfo.isPresent()) {
            throw new ArticleException(TRIPPLAN_EXCEPTION);
        }
        return findAttractionInfo.get();
    }
}
