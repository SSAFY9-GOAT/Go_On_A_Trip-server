package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.hotplace.Favorite;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, FavoriteRepositoryCustom {
    Optional<Favorite> findByMemberAndHotPlace(Member member, HotPlace hotPlace);
}
