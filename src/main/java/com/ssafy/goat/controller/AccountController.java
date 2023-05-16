package com.ssafy.goat.controller;

import com.ssafy.goat.controller.request.LoginRequest;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpSession session
    ) {
        log.debug("로그인 리퀘스트={}", loginRequest);
        LoginMember loginUser = accountService.login(loginRequest.getId(), loginRequest.getPassword());
        session.setAttribute("loginUser", loginUser);
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }
}
