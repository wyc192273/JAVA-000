package com.wyc.exchange;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuchen.wu
 * @date 2020-12-19
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private TransactionService transactionServiceImpl;

    @GetMapping("exchange")
    public Object exchange() {
        transactionServiceImpl.transaction();
        return "success";
    }

}
