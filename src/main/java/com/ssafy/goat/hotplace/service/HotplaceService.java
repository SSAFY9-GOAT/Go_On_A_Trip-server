package com.ssafy.goat.hotplace.service;

import com.ssafy.goat.hotplace.service.dto.AddHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotplaceService {
    Long writeHotPlace(String loginId, AddHotPlaceDto addHotPlaceDto);
}
