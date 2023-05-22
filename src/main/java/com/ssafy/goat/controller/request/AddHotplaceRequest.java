package com.ssafy.goat.controller.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddHotplaceRequest {

    @NotNull
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String desc;
    @NotBlank
    private String visitedDate;
    private MultipartFile file;
}
