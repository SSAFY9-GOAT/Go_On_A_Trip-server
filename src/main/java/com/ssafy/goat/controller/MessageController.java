package com.ssafy.goat.controller;

import com.ssafy.goat.controller.request.FindPwRequest;
import com.ssafy.goat.member.service.AccountService;
import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final AccountService accountService;
    private final MemberService memberService;

    private DefaultMessageService messageService;

    @Value("${MESSAGE_API_KEY}")
    private String API_KEY;
    @Value("${MESSAGE_API_SECRET}")
    private String API_SECRET_KEY;
    private String targetUrl = "http://api.coolsms.co.kr/messages/v4/send";

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    /**
     * 단일 메시지 발송
     */
    @PostMapping("/findPw")
    public Map<String, String> findPw(
            @Valid @RequestBody FindPwRequest request
    ) {
        log.debug("비밀번호 찾기 요청됨 request = {}", request);
        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, "https://api.coolsms.co.kr");
        Map<String, String> resultMap = new HashMap<>();

        String loginId = request.getLoginId();
        StringTokenizer st = new StringTokenizer(request.getPhone(), "-");
        String phone = st.nextToken() + st.nextToken() + st.nextToken();
        log.debug("[비밀번호 찾기] 아이디 =" + loginId);
        log.debug("[비밀번호 찾기] 전화번호 =" + phone);
        if (accountService.getUserPhone(loginId, phone)) {
            log.debug("입력 제대로 되어있음 - 유저 찾음");

            String newPw = getRamdomPassword(12);

            Message message = new Message();
            message.setFrom(phone);
            message.setTo(phone);
            message.setText("[GOAT] 임시번호가 발급되었습니다.\n" + newPw + "\n로그인 후 비밀번호를 변경하세요.");
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));

            Long member = memberService.findAndChangePassword(loginId, newPw);
            resultMap.put("message", SUCCESS);
        } else {
            log.debug("입력 제대로 안됨 - 유저 없음");
            resultMap.put("message", FAIL);
        }
        return resultMap;
    }


    public String getRamdomPassword(int size) {
        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&'};

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i = 0; i < size; i++) {
            idx = sr.nextInt(len);
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
}
