package com.ssafy.goat.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ChangInfoRequest {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String phone;
    @NotBlank
    private String nickname;
}
