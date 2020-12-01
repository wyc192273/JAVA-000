package com.wyc.work.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyc.work.service.TestService;

/**
 * Created by yuchen.wu on 2020-11-29
 */
@RequestMapping("test")
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("ping")
    public Object ping() {
        return "pong";
    }

    @GetMapping("get_name_by_id")
    public Object getNameById(@RequestParam("id") int id) {
        return testService.getNameById(id);
    }

    //应该用post，为了方便测试直接用get
    @GetMapping("update_name_by_id")
    public Object updateNameById(@RequestParam("id") int id, @RequestParam("name") String name) {
        return testService.updateNameById(id, name);
    }
}
