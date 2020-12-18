package com.wyc.exchange;

import lombok.extern.slf4j.Slf4j;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyc.exchange.dto.Account;
import com.wyc.exchange.service.ExchangeService;

/**
 * @author yuchen.wu
 * @date 2020-12-18
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService{

    final private ExchangeService exchangeService;

    /**
     * 这个注入很关键，这样注入就能进入RPC的切面，没有就报错
     * @param exchangeService
     */
    @Autowired(required = false)
    public TransactionServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void transaction() {
        transactionA();
        transactionB();
    }

    private void transactionA() {
        log.info("============py one dubbo try 执行确认付款接口===============");
        Account account = new Account();
        account.setId(1);
        account.setRmb(7);
        account.setDollar(-1);
        exchangeService.exchange(account);
    }

    private void transactionB() {
        log.info("============py two dubbo try 执行确认付款接口===============");
        Account account = new Account();
        account.setId(2);
        account.setDollar(1);
        account.setRmb(-7);
        exchangeService.exchange(account);
    }

    public void confirmOrderStatus() {
        log.info("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus() {
        log.info("=========进行订单cancel操作完成================");
    }
}
