package com.ssafy.goat.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull
    private String id;
    @NotNull
    private String password;
}
