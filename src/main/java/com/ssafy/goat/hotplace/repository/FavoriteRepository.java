package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.hotplace.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, FavoriteRepositoryCustom {
}
