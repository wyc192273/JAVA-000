package com.wyc.rpcfx.order.api.service;

import com.wyc.rpcfx.order.api.bean.Order;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public interface OrderService {

    Order findOrderById(int id);

}
