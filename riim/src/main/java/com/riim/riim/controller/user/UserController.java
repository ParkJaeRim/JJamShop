package com.riim.riim.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.riim.riim.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = { "*" })
@RestController
public class UserController {
    @Autowired
    UserDao userdao;

}