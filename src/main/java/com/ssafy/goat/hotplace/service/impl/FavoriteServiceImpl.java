package com.ssafy.goat.hotplace.service.impl;

import com.ssafy.goat.hotplace.Favorite;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.repository.FavoriteRepository;
import com.ssafy.goat.hotplace.repository.HotplaceRepository;
import com.ssafy.goat.hotplace.service.FavoriteService;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;
    private final HotplaceRepository hotplaceRepository;

    @Override
    public Long likeHotplace(Long memberId, Long hotplaceId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            return null;
        }
        Member member = findMember.get();

        Optional<HotPlace> findHotplace = hotplaceRepository.findById(hotplaceId);
        if (!findHotplace.isPresent()) {
            return null;
        }
        HotPlace hotPlace = findHotplace.get();
        Favorite favorite = Favorite.builder()
                .hotPlace(hotPlace)
                .member(member)
                .build();
        Favorite savedFavorite = favoriteRepository.save(favorite);
        return savedFavorite.getHotPlace().getHotPlaceId();
    }

    @Override
    public List<Long> getUserLikeList(Long memberId) {
        return favoriteRepository.getUserLikeList(memberId);
    }

    @Override
    public Long cancelLike(Long memberId, Long hotplaceId) {
//        favoriteRepository.findByMemberAndHotplace();
        Member member = memberRepository.findById(memberId).get();
        HotPlace hotPlace = hotplaceRepository.findById(hotplaceId).get();
        Optional<Favorite> findFavorite = favoriteRepository.findByMemberAndHotPlace(member, hotPlace);
        if(!findFavorite.isPresent()){
            log.debug("좋아요없음");
            return null;
        }
        Favorite favorite = findFavorite.get();
        favoriteRepository.deleteById(favorite.getFavoriteId());
        return favorite.getFavoriteId();
    }
}
