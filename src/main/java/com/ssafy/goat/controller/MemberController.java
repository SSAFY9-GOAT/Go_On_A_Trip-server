package com.ssafy.goat.controller;

import com.ssafy.goat.controller.request.ChangInfoRequest;
import com.ssafy.goat.controller.request.ChangPasswordRequest;
import com.ssafy.goat.controller.request.RegistRequest;
import com.ssafy.goat.controller.request.WithdrawalRequest;
import com.ssafy.goat.controller.response.MemberResponse;
import com.ssafy.goat.member.dto.ChangUserDto;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/mypage")
    @ApiOperation(value = "마이페이지")
    public MemberResponse mypage(
            @RequestParam(name = "loginId") String loginUser
    ) {
        log.debug("마이페이지");
        return memberService.getUserInfo(loginUser);
    }

    @PostMapping("/modify")
    @ApiOperation(value = "정보 수정하기")
    public int modify(@Valid @RequestBody ChangInfoRequest request) {
        StringTokenizer st = new StringTokenizer(request.getPhone(), "-");
        ChangUserDto dto = ChangUserDto.builder()
                .password(request.getPassword())
                .phone(st.nextToken() + st.nextToken() + st.nextToken())
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();
        Long member = memberService.changeUserInfo(request.getLoginId(), dto);
        if (member != null) {
            return 1;
        } else {
            return -1;
        }
    }

    @PostMapping("/modipyPw")
    @ApiOperation(value = "비밀번호 변경")
    public int modifyPw(@Valid @RequestBody ChangPasswordRequest request) {
        log.debug("비밀번호 요청 : {}", request);
        Long id = memberService.changePassword(request.getId(), request.getOriginalPw(), request.getNewPw());
        if (id == null) {
            return -1;
        }
        return 1;
    }

    @PostMapping("/withdrawal")
    @ApiOperation(value = "회원 탈퇴")
    public int withdrawal(@Valid @RequestBody WithdrawalRequest request) {
        log.debug("회원탈퇴 요청 : {}", request);
        return memberService.withdrawal(request.getLoginId(), request.getLoginPw());
    }
}
