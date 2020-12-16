package com.wyc.rpcfx.provider.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wyc.rpcfx.api.ReflectRpcfxResolver;
import com.wyc.rpcfx.api.RpcfxRequest;
import com.wyc.rpcfx.api.RpcfxResponse;
import com.wyc.rpcfx.server.RpcfxInvoker;

/**
 * Created by yuchen.wu on 2020-12-14
 */
@RestController
public class RpcfxController {

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest rpcfxRequest) {
        ReflectRpcfxResolver reflectRpcfxResolver = new ReflectRpcfxResolver();
        RpcfxInvoker invoker = new RpcfxInvoker(reflectRpcfxResolver, "com.wyc.rpcfx.provider.service.impl");
        return invoker.invoke(rpcfxRequest);
    }

}
