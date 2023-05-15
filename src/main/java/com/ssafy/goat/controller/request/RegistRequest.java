package com.ssafy.goat.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String loginPw;
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]*$")
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String phone;
    @NotBlank
    @Size(max = 4, min = 4)
    private String birth;
    @NotBlank
    @Size(max = 4, min = 4)
    private String birthyear;
    @NotBlank
    @Size(max = 1)
    private String gender;
    @NotBlank
    private String nickname;
}
