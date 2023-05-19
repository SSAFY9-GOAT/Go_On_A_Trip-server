package com.ssafy.goat.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FindPwRequest {
    @NotBlank
    @NotNull
    private String loginId;
    @NotBlank
    @NotNull
    private String phone;
}
