package com.riim.riim.controller.user;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.riim.riim.dao.UserDao;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = { "*" })
@RestController
public class UserController {
    @Autowired
    UserDao userdao;

    // 카카오 인가코드 발급
    @GetMapping("/aa")
    public Object aa(@RequestParam("code") String code) {
        String[] Token = getAccessToken(code);
        String access_Token = Token[0];
        HashMap<String, String> userinfo = getUserInfo(access_Token);
        String kakao_email = userinfo.get("kakao_email");
        String kakao_id = userinfo.get("kakao_id");

        RedirectView redirectView = new RedirectView();

        if (userdao.findNameByKakaoid(kakao_id) != null) {

        } else {

            redirectView.setUrl("http://localhost:3000/user/join"); // 회원가입 폼으로 이동
            redirectView.addStaticAttribute("kakao_email", kakao_email);
            redirectView.addStaticAttribute("kakao_id", kakao_id);
        }
        return redirectView;
    }

    public String[] getAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String token[] = new String[2];

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=d8a07bcf4f4992d4a841f569e9e68f57");
            sb.append("&redirect_uri=http://localhost:8080/aa");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
                System.out.println(line);
            }
            // {"access_token":"","token_type":"bearer","refresh_token":""
            // ,"expires_in":21599,"scope":"account_email","refresh_token_expires_in":5183999}

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            access_Token = jsonObj.get("access_token").toString();
            refresh_Token = jsonObj.get("refresh_token").toString();
            token[0] = access_Token;
            token[1] = refresh_Token;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public HashMap<String, String> getUserInfo(String access_Token) {
        HashMap<String, String> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            // int responseCode = conn.getResponseCode();
            // System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            // System.out.println("response body : " + result);

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
            String userid = jsonObj.get("id").toString();

            JSONObject accountInfo = (JSONObject) jsonObj.get("kakao_account");
            String kakaoemail = accountInfo.get("email").toString();
            // {"email_needs_agreement":false,"is_email_valid":true,"is_email_verified":true,"has_email":true,"email":""}
            userInfo.put("kakao_id", userid);
            userInfo.put("kakao_email", kakaoemail);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}