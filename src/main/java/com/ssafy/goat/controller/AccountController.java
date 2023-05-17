package com.ssafy.goat.controller;

import com.ssafy.goat.common.exception.LoginException;
import com.ssafy.goat.controller.request.LoginRequest;
import com.ssafy.goat.jwt.Token;
import com.ssafy.goat.jwt.service.JwtServiceImpl;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final JwtServiceImpl jwtService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public Result<?> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        log.debug("로그인 리퀘스트={}", loginRequest);
        LoginMember loginUser = null;
        try {
            loginUser = accountService.login(loginRequest.getId(), loginRequest.getPassword());
        } catch (LoginException e) {
            log.error("로그인 실패 : {}", e);
            return new Result<>(null, "아이디 혹은 비밀번호를 확인해주세요.");
        }
        String accessToken = jwtService.createAccessToken("loginUser", loginUser);// key, data
        String refreshToken = jwtService.createRefreshToken("loginId", loginUser.getLoginId());// key, data
        accountService.saveRefreshToken(loginRequest.getId(), refreshToken);
        log.debug("로그인 accessToken 정보 : {}", accessToken);
        log.debug("로그인 refreshToken 정보 : {}", refreshToken);
        Token token = Token.builder()
                .access(accessToken)
                .refresh(refreshToken)
                .build();
        return new Result<>(token, null);
    }

    @ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
    @GetMapping("/logout/{userid}")
    public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            accountService.deleteRefreshToken(userid);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T token;

        private String result;
    }
}
