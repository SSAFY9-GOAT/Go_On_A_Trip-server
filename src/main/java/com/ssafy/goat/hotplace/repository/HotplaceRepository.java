package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.hotplace.HotPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotplaceRepository extends JpaRepository<HotPlace, Long> {
}
