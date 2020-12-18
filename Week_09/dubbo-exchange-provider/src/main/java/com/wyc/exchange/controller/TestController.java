package com.wyc.exchange.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyc.exchange.mapper.AccountMapper;

/**
 * @author yuchen.wu
 * @date 2020-12-16
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private AccountMapper accountMapper;

    @GetMapping("query_account_by_id")
    public Object queryAccountById(@RequestParam("id") int id) {
        return accountMapper.queryOne(id);
    }

}
