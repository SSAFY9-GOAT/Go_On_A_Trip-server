package com.ssafy.goat.hotplace.service.impl;

import com.ssafy.goat.common.domain.UploadFile;
import com.ssafy.goat.common.exception.LoginException;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.repository.HotplaceRepository;
import com.ssafy.goat.hotplace.repository.UploadFileRepository;
import com.ssafy.goat.hotplace.service.HotplaceService;
import com.ssafy.goat.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotplaceServiceImpl implements HotplaceService {

    private final HotplaceRepository hotplaceRepository;
    private final UploadFileRepository uploadFileRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long writeHotPlace(String loginId, AddHotPlaceDto addHotPlaceDto) {
        log.debug("[핫플레이스] 등록 addDto = {}", addHotPlaceDto);
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            throw new LoginException("로그인 후 이용해주세요");
        }

        Member member = findMember.get();

        UploadFile uploadFile = UploadFile.builder()
                .storeFileName(addHotPlaceDto.getStoreFileName())
                .uploadFileName(addHotPlaceDto.getOriginalFilename())
                .build();
        UploadFile savedFile = uploadFileRepository.save(uploadFile);
        log.debug("[핫플레이스] 파일 저장됨 = {}", savedFile.getFileId());
        log.debug("[핫플레이스] 파일 저장명 = {}", savedFile.getStoreFileName());
        log.debug("[핫플레이스] 파일 원본이름 = {}", savedFile.getUploadFileName());
        log.debug("[핫플레이스] desc = {}", addHotPlaceDto.getDesc());
        HotPlace hotPlace = HotPlace.builder()
                .name(addHotPlaceDto.getName())
                .desc(addHotPlaceDto.getDesc())
                .latitude(addHotPlaceDto.getLatitude())
                .longitude(addHotPlaceDto.getLongitude())
                .hit(0)
                .vote(0)
                .visitedDate(addHotPlaceDto.getVisitedDate())
                .uploadFile(savedFile)
                .member(member)
                .build();
        log.debug("[핫플레이스] 저장 객체 = {}", hotPlace);
        HotPlace savedHotplace = hotplaceRepository.save(hotPlace);
        return savedHotplace.getHotPlaceId();
    }
}
