package com.ssafy.goat.controller;

import com.ssafy.goat.controller.request.RegistRequest;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.StringTokenizer;

import static com.ssafy.goat.member.Authority.CLIENT;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/regist")
    @ApiOperation(value = "회원가입")
    public int regist(
            @Valid @RequestBody RegistRequest request
    ) {
        log.debug("회원가입 리퀘스트={}", request);
        StringTokenizer st = new StringTokenizer(request.getPhone(), "-");
        String phone = st.nextToken() + st.nextToken() + st.nextToken();
        log.debug("전화번호 = {}", phone);

        Long joinId = memberService.signUp(MemberAddDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .username(request.getUserName())
                .email(request.getEmail())
                .phone(phone)
                .birth(request.getBirth())
                .birthyear(request.getBirthyear())
                .gender(request.getGender())
                .nickname(request.getNickname())
                .authority(CLIENT)
                .snsId("-1")
                .build());
        log.debug("회원가입 joinId = {}", joinId);
        return 1;
    }
}
