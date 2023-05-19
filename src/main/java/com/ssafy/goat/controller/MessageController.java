package com.ssafy.goat.controller;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    final DefaultMessageService messageService;

    @Value("${MESSAGE_API_KEY}")
    private String API_KEY;
    @Value("${MESSAGE_API_SECRET}")
    private String API_SECRET_KEY;

    public MessageController() {
        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, "https://api.coolsms.co.kr");
    }
}
