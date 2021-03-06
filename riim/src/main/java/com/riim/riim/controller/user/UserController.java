package com.riim.riim.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.riim.riim.config.security.JwtTokenProvider;
import com.riim.riim.dao.UserDao;
import com.riim.riim.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = { "*" })
@RestController
public class UserController {

    @Autowired
    UserDao userdao;

    @Autowired
    KakaoLogin kakaologin;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "hi";
    }

    @GetMapping("/klogin")
    public HashMap<String, String> klogin(@RequestParam String authorize_code) {
        String access_token = kakaologin.getAccessToken(authorize_code);
        HashMap<String, String> userinfo = kakaologin.getUserInfo(access_token);
        return userinfo;
    }

    // "/user/*로 접근할 경우, user role로 인해 걸러져야함. 걸러지는건 되는것같은데 접근이 안됨"
    @GetMapping("/user/test")
    public static String testuser() {
        System.out.println("gg");
        return "user페이지입니다.";
    }

    @PostMapping("/join")
    public void join(@RequestBody User user) {
        userdao.save(User.builder().email(user.getEmail()).password(passwordEncoder.encode(user.getPassword())).uname(user.getUname())
                .roles(Collections.singletonList("USER")).build());
    }

    // SpringSecurity 사용 시, email은 카카오이메일로 / password는 카카오아이디로
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User member = userdao.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String Token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        return Token;
    }

    @PostMapping("/isUser")
    public String isUser(@RequestBody User user){
        Optional<User> member = userdao.findByEmail(user.getEmail());
        if(member.isEmpty()){
            return "NotExist";
        }else{
        return "Exist";}
    }
}