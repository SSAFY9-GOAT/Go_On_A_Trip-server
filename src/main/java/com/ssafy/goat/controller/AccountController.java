package com.ssafy.goat.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.goat.common.exception.LoginException;
import com.ssafy.goat.controller.request.LoginRequest;
import com.ssafy.goat.jwt.Token;
import com.ssafy.goat.jwt.service.JwtServiceImpl;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.service.AccountService;
import com.ssafy.goat.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static com.ssafy.goat.member.Authority.CLIENT;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final JwtServiceImpl jwtService;
    private final MemberService memberService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Value("${NAVER_CLIENT_SECRET}")
    private String NAVER_CLIENT_SECRET;

    @GetMapping("/naverLogin")
    @ApiOperation(value = "네이버로 로그인 하기")
    public Result<?> naverLogin(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state) {
        Map<String, Object> result = new HashMap<>();
        log.debug("[네아로] state = {}", state);

        String clientId = "TjbFSfFWxEGU5lsUKvzz";//애플리케이션 클라이언트 아이디값";

        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + NAVER_CLIENT_SECRET;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        log.debug("[네아로] apiURL = {}", apiURL);
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;

            log.debug("[네아로] 응답 = {}", responseCode);

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                log.debug("[네아로] 이거 왜 = {}", con.getInputStream());
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) { // 성공적으로 토큰을 가져온다면
                String id;
                String tmp;
                JsonParser parser = new JsonParser();

                log.debug("[네아로] res = {}", res);

                JsonElement accessElement = parser.parse(res.toString());
                access_token = accessElement.getAsJsonObject().get("access_token").getAsString();
                log.debug("[네아로] access_token = {}", access_token);

                tmp = getUserInfo(access_token);
                JsonElement userInfoElement = parser.parse(tmp);
                id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();

                Member snsUser = memberService.getSnsUser(id);
                if (snsUser == null) {
                    String loginId = getRamdomString(12);
                    log.debug("[네아로] loginId = {}", loginId);
                    String loginPw = loginId;
                    String username = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
                    log.debug("[네아로] username = {}", username);
                    String email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
                    log.debug("[네아로] email = {}", email);
                    String nickName = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("nickname").getAsString();
                    log.debug("[네아로] nickName = {}", nickName);

                    StringTokenizer st = new StringTokenizer(userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("mobile").getAsString(), "-");
                    String phone = st.nextToken() + st.nextToken() + st.nextToken();
                    log.debug("[네아로] phone = {}", phone);

                    st = new StringTokenizer(userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("birthday").getAsString(), "-");
                    String birth = st.nextToken() + st.nextToken();
                    log.debug("[네아로] birth = {}", birth);
                    String birthyear = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("birthyear").getAsString();
                    log.debug("[네아로] birthyear = {}", birthyear);
                    String gender = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("gender").getAsString();
                    log.debug("[네아로] gender = {}", gender);

                    Long joinId = memberService.signUp(MemberAddDto.builder()
                            .loginId(loginId)
                            .loginPw(loginPw)
                            .username(username)
                            .email(email)
                            .phone(phone)
                            .birth(birth)
                            .birthyear(birthyear)
                            .gender(gender)
                            .nickname(nickName)
                            .authority(CLIENT)
                            .snsId("1")
                            .build());

                    String snsUserId = memberService.snsSignUp(joinId, id);
                    snsUser = memberService.getSnsUser(snsUserId);
                }

                LoginMember loginUser = null;
                try {
                    loginUser = accountService.login(snsUser.getLoginId(), snsUser.getLoginPw());
                } catch (LoginException e) {
                    log.error("[네아로] 로그인 실패 : {}", e);
                    return new Result<>(null, "아이디 혹은 비밀번호를 확인해주세요.");
                }
                String accessToken = jwtService.createAccessToken("loginUser", loginUser);// key, data
                String refreshToken = jwtService.createRefreshToken("loginId", loginUser.getLoginId());// key, data
                accountService.saveRefreshToken(snsUser.getLoginId(), refreshToken);
                log.debug("[네아로] 로그인 accessToken 정보 : {}", accessToken);
                log.debug("[네아로] 로그인 refreshToken 정보 : {}", refreshToken);
                Token token = Token.builder()
                        .access(accessToken)
                        .refresh(refreshToken)
                        .build();
                return new Result<>(token, null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Result<>(null, "아이디 혹은 비밀번호를 확인해주세요.");
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public Result<?> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        log.debug("로그인 리퀘스트={}", loginRequest);
        LoginMember loginUser = null;
        try {
            loginUser = accountService.login(loginRequest.getId(), loginRequest.getPassword());
        } catch (LoginException e) {
            log.error("로그인 실패 : {}", e);
            return new Result<>(null, "아이디 혹은 비밀번호를 확인해주세요.");
        }
        String accessToken = jwtService.createAccessToken("loginUser", loginUser);// key, data
        String refreshToken = jwtService.createRefreshToken("loginId", loginUser.getLoginId());// key, data
        accountService.saveRefreshToken(loginRequest.getId(), refreshToken);
        log.debug("로그인 accessToken 정보 : {}", accessToken);
        log.debug("로그인 refreshToken 정보 : {}", refreshToken);
        Token token = Token.builder()
                .access(accessToken)
                .refresh(refreshToken)
                .build();
        return new Result<>(token, null);
    }

    @GetMapping("/user/{loginId}")
    @ApiOperation(value = "토큰으로 로그인 유저 정보 받아오기")
    public ResponseEntity<?> getLoginMember(
            @PathVariable String loginId,
            HttpServletRequest request
    ) {
        LoginMember member = null;
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                member = accountService.getLoginMember(loginId);
                resultMap.put("message", SUCCESS);
                resultMap.put("loginUser", member);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
    @GetMapping("/logout/{userid}")
    public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
        log.debug("로그아웃 요청 : {}", userid);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            accountService.deleteRefreshToken(userid);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T token;

        private String result;
    }

    private String getUserInfo(String access_token) {
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            log.debug("[네아로] 유저정보 요청 응답코드 = {}", responseCode);
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            log.debug("[네아로] 유저정보 요청 res = {}", res);
            return res.toString();
        } catch (Exception e) {
            System.err.println(e);
            return "Err";
        }
    }

    public String getRamdomString(int size) {
        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };

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
