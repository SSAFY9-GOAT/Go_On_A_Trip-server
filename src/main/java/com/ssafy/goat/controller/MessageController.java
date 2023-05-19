package com.ssafy.goat.controller;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    final DefaultMessageService messageService;

    public MessageController(){
        this.messageService = NurigoApp.INSTANCE.initialize()
    }
}
