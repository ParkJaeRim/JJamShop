package com.riim.riim.controller.user;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.riim.riim.dao.UserDao;
import com.riim.riim.model.User;

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

    @GetMapping("/")
    public String index() {
        return "hi";
    }

    @GetMapping("/Alluser")
    public String alluser(@RequestParam int uid) {
        User user = userdao.findNameByUid(uid);
        return user.getUname();
    }

    @GetMapping("/hi")
    public String login() {
        return "https://kauth.kakao.com/oauth/authorize?client_id=d8a07bcf4f4992d4a841f569e9e68f57&redirect_uri=http://localhost:8080/aa&response_type=code";
    }

    // 카카오 인가코드 발급
    @GetMapping("/aa")
    public String aa(@RequestParam("code") String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

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
            // System.out.println("responseCode : " + responseCode);
            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            // System.out.println("response body : " + result);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            access_Token = jsonObj.get("access_token").toString();
            refresh_Token = jsonObj.get("refresh_token").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_Token;
    }
}