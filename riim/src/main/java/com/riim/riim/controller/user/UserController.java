package com.riim.riim.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.riim.riim.dao.UserDao;
import com.riim.riim.model.User;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/join")
    public void join(@RequestBody User request) {
        userdao.save(request);
    }

}