package com.wyc.rpcfx.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wyc.rpcfx.client.RpcfxProxy;
import com.wyc.rpcfx.order.api.bean.Order;
import com.wyc.rpcfx.order.api.service.OrderService;
import com.wyc.rpcfx.user.api.bean.User;
import com.wyc.rpcfx.user.api.service.UserService;

/**
 * Created by yuchen.wu on 2020-12-14
 */
@SpringBootApplication
public class RpcfxClientApplication {

    public static void main(String[] args) {
        UserService userService = RpcfxProxy.create(UserService.class, "http://localhost:8081/");
        User user = userService.findUserById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        OrderService orderService = RpcfxProxy.create(OrderService.class, "http://localhost:8081/");
        Order order = orderService.findOrderById(1992129);
        System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));
    }
}
