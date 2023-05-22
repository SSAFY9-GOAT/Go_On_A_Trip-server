package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.controller.request.HotPlaceListRequest;
import com.ssafy.goat.controller.response.HotPlaceListResponse;

import java.util.List;

public interface HotplaceRepositoryCustom {
    List<HotPlaceListResponse> searchByCondition(HotPlaceListRequest request);
}
