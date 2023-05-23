package com.ssafy.goat.hotplace.service;

import com.ssafy.goat.controller.request.HotPlaceListRequest;
import com.ssafy.goat.controller.response.HotPlaceDetail;
import com.ssafy.goat.controller.response.HotPlaceListResponse;
import com.ssafy.goat.hotplace.service.dto.AddHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HotplaceService {
    Long writeHotPlace(String loginId, AddHotPlaceDto addHotPlaceDto);

    List<HotPlaceListResponse> getHotplaceList(HotPlaceListRequest request);

    HotPlaceDetail getHotplace(Long hotplaceId);

    int updateHit(Long hotPlaceId);
}
