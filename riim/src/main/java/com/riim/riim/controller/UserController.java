package com.riim.riim.controller;

import org.springframework.web.bind.annotation.RestController;

import com.riim.riim.dao.UserDao;
import com.riim.riim.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
    @Autowired
    UserDao userdao;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/Alluser")
    public String alluser(@RequestParam int uid) {
        User user = userdao.findNameByUid(uid);
        return user.getUname();
    }

}