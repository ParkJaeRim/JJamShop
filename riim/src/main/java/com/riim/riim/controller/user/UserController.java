package com.riim.riim.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.riim.riim.dao.UserDao;

import java.util.HashMap;

import com.riim.riim.controller.user.KakaoLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        System.out.println(authorize_code);
        String access_token = kakaologin.getAccessToken(authorize_code);
        System.out.println(access_token);
        HashMap<String, String> userinfo = kakaologin.getUserInfo(access_token);
        System.out.println(userinfo.get("email"));
        System.out.println(userinfo.get("id"));
        return userinfo;
    }

}