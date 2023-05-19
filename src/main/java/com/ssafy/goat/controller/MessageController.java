package com.ssafy.goat.controller;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 단일 메시지 발송 예제
     */
    @PostMapping("/findPw")
    public SingleMessageSentResponse findPw() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("발신번호 입력");
        message.setTo("수신번호 입력");
        message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
}
