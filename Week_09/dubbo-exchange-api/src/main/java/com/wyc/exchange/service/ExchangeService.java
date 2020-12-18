package com.wyc.exchange.service;

import org.dromara.hmily.annotation.Hmily;

import com.wyc.exchange.dto.Account;

/**
 * @author yuchen.wu
 * @date 2020-12-16
 */

public interface ExchangeService {

    @Hmily
    boolean exchange(Account account);

}
