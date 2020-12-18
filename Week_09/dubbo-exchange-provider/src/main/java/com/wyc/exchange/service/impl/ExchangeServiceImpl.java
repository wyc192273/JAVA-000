package com.wyc.exchange.service.impl;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyc.exchange.dto.Account;
import com.wyc.exchange.mapper.AccountMapper;
import com.wyc.exchange.service.ExchangeService;

/**
 * @author yuchen.wu
 * @date 2020-12-16
 */
@Slf4j
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Resource
    private AccountMapper accountMapper;


    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean exchange(Account account) {
        boolean isSuccess = accountMapper.payment(account);
        log.info("py account : + " + account.getId() + " try result: " + isSuccess);
        log.info("py account : + " + account.getId() + " try data: " + accountMapper.queryOne(account.getId()));
        return isSuccess;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(Account account) {
        log.info("============dubbo tcc 执行确认付款接口===============");
        log.info("param account : " + account.toString());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Account account) {
        log.info("============ dubbo tcc 执行取消付款接口===============");
        log.info("param account : " + account.toString());
        return true;
    }

}
