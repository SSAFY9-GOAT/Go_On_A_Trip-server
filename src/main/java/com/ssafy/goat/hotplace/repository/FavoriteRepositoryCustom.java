package com.ssafy.goat.hotplace.repository;

import java.util.List;

public interface FavoriteRepositoryCustom {

    List<Long> getUserLikeList(Long memberId);
}
