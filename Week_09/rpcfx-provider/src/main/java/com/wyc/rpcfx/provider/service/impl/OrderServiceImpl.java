package com.wyc.rpcfx.provider.service.impl;

import com.wyc.rpcfx.order.api.bean.Order;
import com.wyc.rpcfx.order.api.service.OrderService;

/**
 * Created by yuchen.wu on 2020-12-14
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(1, "订单1", 100.0);
    }
}
