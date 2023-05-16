package com.ssafy.goat.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WithdrawalRequest {

    @NotBlank
    private Long id;
    @NotBlank
    private String loginPw;
}
