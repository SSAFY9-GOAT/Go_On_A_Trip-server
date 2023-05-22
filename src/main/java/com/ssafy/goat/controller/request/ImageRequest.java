package com.ssafy.goat.controller.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageRequest {
    private MultipartFile file;
}
