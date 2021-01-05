package com.wyc.cache.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyc.cache.entity.User;
import com.wyc.cache.service.UserService;

@RestController
@EnableAutoConfiguration
public class UserController {

    @Resource
    private UserService userService;
    
    @RequestMapping("/user/find")
    User find(Integer id) {
        return userService.find(id);
    }

    @RequestMapping("/user/list")
    List<User> list() {
        return userService.list();
    }
}