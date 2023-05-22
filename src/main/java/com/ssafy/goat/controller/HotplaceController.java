package com.ssafy.goat.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.ssafy.goat.controller.request.AddHotplaceRequest;
import com.ssafy.goat.hotplace.service.HotplaceService;
import com.ssafy.goat.hotplace.service.dto.AddHotPlaceDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hotplaces")
public class HotplaceController {

    private final HotplaceService hotplaceService;

    @Value("${file.imgPath}")
    public String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @PostMapping("/write")
    @ApiOperation(value = "핫플레이스 등록")
    public Long write(@Valid AddHotplaceRequest request) throws IOException, ImageProcessingException {
        log.debug("[핫플레이스] 등록 요청 = {}", request);
        MultipartFile multipartFile = request.getFile();

        String originalFilename = multipartFile.getOriginalFilename();

        int pos = originalFilename.lastIndexOf(".");

        String ext = originalFilename.substring(pos + 1);
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + ext;
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        log.debug("[핫플레이스] 파일 저장 완료 = {}", getFullPath(storeFileName));

        log.debug("[핫플레이스] 위도 경도 추출 시작");
        File file = new File(getFullPath(storeFileName));

        Metadata metadata = ImageMetadataReader.readMetadata(file);
        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
        String pdsLat = "";
        String pdsLon = "";
        if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {

            pdsLat = String.valueOf(gpsDirectory.getGeoLocation().getLatitude());
            pdsLon = String.valueOf(gpsDirectory.getGeoLocation().getLongitude());
        }
        double lat = Double.parseDouble(pdsLat);    //위도
        double lon = Double.parseDouble(pdsLon);
        log.debug("[핫플레이스] 위도 ={} 경도 = {}", lat, lon);
        AddHotPlaceDto addHotPlaceDto = AddHotPlaceDto.builder()
                .name(request.getName())
                .desc(request.getDesc())
                .latitude(lat)
                .longitude(lon)
                .visitedDate(request.getVisitedDate())
                .originalFilename(originalFilename)
                .storeFileName(storeFileName)
                .build();
        return hotplaceService.writeHotPlace(request.getLoginId(), addHotPlaceDto);
    }
}