package com.ssafy.goat.hotplace.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavoriteService {
    Long likeHotplace(Long memberId, Long hotplaceId);

    List<Long> getUserLikeList(Long memberId);

    Long cancelLike(Long favoriteId);
}
